package net.inpercima.serverconnector;

import java.io.InputStream;
import java.util.Properties;
import java.util.stream.Collectors;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class Application implements CommandLineRunner {

    @Autowired
    private ServerProperties properties;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        connect();
    }

    private void connect() {
        try {
            final Properties config = new Properties();
            config.put("StrictHostKeyChecking", "no");
            final JSch jsch = new JSch();
            final Session session = jsch.getSession(properties.getUser(), properties.getHost(), properties.getPort());
            session.setPassword(properties.getPassword());
            session.setConfig(config);
            session.connect();
            log.info("Connected");

            final Channel channel = session.openChannel("exec");

            final String commands = properties.getCommands().stream().map(command -> command.toString())
                    .collect(Collectors.joining(" ; "));
            ((ChannelExec) channel).setCommand(commands);

            channel.setInputStream(null);
            ((ChannelExec) channel).setErrStream(System.err);

            final InputStream in = channel.getInputStream();
            channel.connect();
            final byte[] tmp = new byte[1024];
            while (true) {
                while (in.available() > 0) {
                    final int i = in.read(tmp, 0, 1024);
                    if (i < 0) {
                        break;
                    }
                    log.info("Server output: {}", new String(tmp, 0, i));
                }
                if (channel.isClosed()) {
                    log.info("exit status: {}", channel.getExitStatus());
                    break;
                }
                try {
                    Thread.sleep(1000);
                } catch (final Exception ee) {
                }
            }
            channel.disconnect();
            session.disconnect();
            log.info("DONE");
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

}
