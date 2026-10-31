import java.text.SimpleDateFormat;
import java.util.Date;

public class MeetingRoom {
  private Integer data = null;
  private Integer targetConsumerId = null;
  private final Object lock = new Object();

  public void putData(int supplierId, int data) throws InterruptedException {
    synchronized (lock) {
      while (this.data != null) {
        System.out.println(getTimestamp() + " Supplier " + supplierId + " enters waiting room (data present)");
        lock.wait();
      }
      System.out.println(getTimestamp() + " Supplier " + supplierId + " enters meeting room");
      this.data = data;
      this.targetConsumerId = supplierId;
      System.out
          .println(getTimestamp() + " Supplier " + supplierId + " leaves data " + data + " for consumer " + supplierId);
      lock.notifyAll();
    }
  }

  public int getData(int consumerId) throws InterruptedException {
    synchronized (lock) {
      while (this.data == null || this.targetConsumerId != consumerId) {
        System.out.println(getTimestamp() + " Consumer " + consumerId + " enters waiting room (no/wrong data)");
        lock.wait();
      }
      System.out.println(getTimestamp() + " Consumer " + consumerId + " enters meeting room");
      int retrievedData = this.data;
      System.out.println(getTimestamp() + " Consumer " + consumerId + " removes data " + retrievedData);
      this.data = null;
      this.targetConsumerId = null;
      lock.notifyAll();
      return retrievedData;
    }
  }

  public String getTimestamp() {
    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSS");
    return sdf.format(new Date()) + " ";
  }
}