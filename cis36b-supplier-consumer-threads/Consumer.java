import java.util.Random;

public class Consumer implements Runnable {
  private final MeetingRoom room;
  private final int consumerId;
  private final Random random = new Random();

  public Consumer(MeetingRoom room, int consumerId) {
    this.room = room;
    this.consumerId = consumerId;
  }

  @Override
  public void run() {
    try {
      while (true) {
        int data = room.getData(consumerId);

        int sleepTime = random.nextInt(1001);
        System.out.println(room.getTimestamp() + " Consumer " + consumerId + " sleeps " + sleepTime + "ms");
        Thread.sleep(sleepTime);
      }
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      System.out.println("Consumer " + consumerId + " interrupted");
    }
  }
}