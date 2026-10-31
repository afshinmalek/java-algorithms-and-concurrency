import java.util.Random;

public class Supplier implements Runnable {
  private final MeetingRoom room;
  private final int supplierId;
  private final Random random = new Random();

  public Supplier(MeetingRoom room, int supplierId) {
    this.room = room;
    this.supplierId = supplierId;
  }

  @Override
  public void run() {
    try {
      while (true) {
        int sleepTime = random.nextInt(1001);
        System.out.println(room.getTimestamp() + " Supplier " + supplierId + " sleeps " + sleepTime + "ms");
        Thread.sleep(sleepTime);

        int data = random.nextInt(100) + 1;
        System.out.println(room.getTimestamp() + " Supplier " + supplierId + " produces data " + data);

        room.putData(supplierId, data);
      }
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      System.out.println("Supplier " + supplierId + " interrupted");
    }
  }
}