import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.time.Instant;
import java.util.Map;


public class Main
{
    public static void main(String[] args)
    {
        Map<String, String> env = System.getenv();

        if(env.get("SIMULATED_DEVICE_ID") == null || env.get("SIMULATED_DEVICE_ID").equals(""))
        {
            System.err.println("SIMULATED_DEVICE_ID environment variable is not defined!");
            return;
        }

        String ID = env.get("SIMULATED_DEVICE_ID");

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("rabitmq");
        factory.setAutomaticRecoveryEnabled(true);

        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel())
        {
            channel.queueDeclare("device_messages", false, false, false, null);

            while(true)
            {
                double measurement = Math.random() * 200; //maximum consumption = 200

                String message = buildMessage(Integer.parseInt(ID), measurement);
                channel.basicPublish("", "device_messages", null, message.getBytes(StandardCharsets.UTF_8));
                System.out.println("[x] Sent '" + message + "'");

                Thread.sleep( 20 * 1000);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private static String buildMessage(Integer id, double consumption)
    {
        long timestamp = Date.from(Instant.now()).getTime();

        StringBuilder sb = new StringBuilder();
        sb.append("{\"device_id\":").append(id.toString()).append(",");
        sb.append("\"timestamp\":").append(timestamp).append(",");
        sb.append("\"measurement_value\":").append(consumption).append("}");

        return sb.toString();
    }
}
