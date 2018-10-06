//package cn.com.tsjx.common.logger;
//
//import java.util.concurrent.BlockingQueue;
//import java.util.concurrent.LinkedBlockingQueue;
//import java.util.concurrent.RejectedExecutionHandler;
//import java.util.concurrent.ThreadPoolExecutor;
//import java.util.concurrent.TimeUnit;
//
//import javax.jms.Destination;
//import javax.jms.JMSException;
//import javax.jms.MapMessage;
//import javax.jms.Message;
//import javax.jms.Session;
//
//import org.apache.activemq.ActiveMQConnectionFactory;
//import org.apache.activemq.command.ActiveMQQueue;
//import org.apache.activemq.command.ActiveMQTopic;
//import org.apache.activemq.pool.PooledConnectionFactory;
//import org.springframework.jms.core.JmsTemplate;
//import org.springframework.jms.core.MessageCreator;
//
///**
// * JMS消息发送。
// * @author liwei
// */
//class JMSMessageAdapter {
//
//	private ThreadPoolExecutor executorService;
//	private JmsTemplate jmsTemplate;
//	private Destination destination;
//	private Configuration configuration;
//
//	public JMSMessageAdapter(Configuration configuration) {
//		this.configuration = configuration;
//		// 线程池
//		int threadNum = configuration.getSendThreadNum();
//		int queueDepth = configuration.getSendQueueDepth();
//		BlockingQueue<Runnable> executorQueue = new LinkedBlockingQueue<Runnable>(queueDepth);
//		this.executorService = new ThreadPoolExecutor(threadNum, threadNum, 0L, TimeUnit.MILLISECONDS, executorQueue);
//		this.executorService.setRejectedExecutionHandler(new RejectedExecutionHandler() {
//			// 队列溢出时，使用Log4j记录日志
//			public void rejectedExecution(Runnable runnable, ThreadPoolExecutor executor) {
//				if (runnable instanceof SendMessageTask) {
//					SendMessageTask task = (SendMessageTask) runnable;
//					DefaultBusiLogger.getInstance(JMSMessageAdapter.this.configuration)._log(
//							"JMS QUEUE FULL. " + task.message);
//				}
//			}
//		});
//		// JMS连接处理
//		PooledConnectionFactory connectionFactory = new PooledConnectionFactory();
//		connectionFactory.setConnectionFactory(new ActiveMQConnectionFactory(configuration.getServerUrl()));
//		connectionFactory.setMaxConnections(configuration.getMaxConnectNum());
//		this.jmsTemplate = new JmsTemplate();
//		this.jmsTemplate.setConnectionFactory(connectionFactory);
//		if (configuration.getMessageType().equalsIgnoreCase("topic")) {
//			this.destination = new ActiveMQTopic(configuration.getDestinationName());
//		} else {
//			this.destination = new ActiveMQQueue(configuration.getDestinationName());
//		}
//		this.jmsTemplate.setDefaultDestination(destination);
//	}
//
//	public void sender(String type, String message) {
//		this.executorService.execute(new SendMessageTask(type, message));
//	}
//
//	private class SendMessageTask implements Runnable {
//		private String type;
//		private String message;
//		private long timestamp;
//
//		private SendMessageTask(String type, String message) {
//			this.type = type;
//			this.message = message;
//			this.timestamp = System.currentTimeMillis();
//		}
//
//		public void run() {
//			jmsTemplate.send(destination, new MessageCreator() {
//				public Message createMessage(Session session) throws JMSException {
//					MapMessage msg = session.createMapMessage();
//					msg.setString("type", type);
//					msg.setString("message", message);
//					msg.setLong("timestamp", timestamp);
//					System.out.println(msg);
//					return msg;
//				}
//			});
//		}
//	}
//
//	private static JMSMessageAdapter INSTANCE = null;
//
//	public static void init(Configuration configuration) {
//		if (INSTANCE == null) {
//			INSTANCE = new JMSMessageAdapter(configuration);
//		}
//	}
//
//	public synchronized static JMSMessageAdapter getInstance(Configuration configuration) {
//		init(configuration);
//		return INSTANCE;
//	}
//}
