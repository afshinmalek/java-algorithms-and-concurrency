public class SupplierConsumerSimulation {
  public static void main(String[] args) {
    MeetingRoom room = new MeetingRoom();
    Thread[] suppliers = new Thread[10];
    Thread[] consumers = new Thread[10];

    for (int i = 0; i < 10; i++) {
      suppliers[i] = new Thread(new Supplier(room, i + 1), "Supplier-" + (i + 1));
    }

    for (int i = 0; i < 10; i++) {
      consumers[i] = new Thread(new Consumer(room, i + 1), "Consumer-" + (i + 1));
    }

    for (Thread supplier : suppliers) {
      supplier.start();
    }
    for (Thread consumer : consumers) {
      consumer.start();
    }

    try {
      Thread.sleep(5000);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }

    for (Thread supplier : suppliers) {
      supplier.interrupt();
    }
    for (Thread consumer : consumers) {
      consumer.interrupt();
    }
  }
}

// @formatter:off

/*
 * java SupplierConsumerSimulation
12:04:28.571  Consumer 1 enters waiting room (no/wrong data)
12:04:28.571  Supplier 4 sleeps 174ms
12:04:28.571  Supplier 8 sleeps 995ms
12:04:28.571  Supplier 2 sleeps 443ms
12:04:28.571  Supplier 6 sleeps 496ms
12:04:28.571  Supplier 3 sleeps 637ms
12:04:28.571  Supplier 10 sleeps 429ms
12:04:28.571  Supplier 5 sleeps 255ms
12:04:28.571  Supplier 9 sleeps 957ms
12:04:28.571  Supplier 1 sleeps 915ms
12:04:28.571  Supplier 7 sleeps 550ms
12:04:28.586  Consumer 10 enters waiting room (no/wrong data)
12:04:28.586  Consumer 9 enters waiting room (no/wrong data)
12:04:28.586  Consumer 8 enters waiting room (no/wrong data)
12:04:28.586  Consumer 7 enters waiting room (no/wrong data)
12:04:28.587  Consumer 6 enters waiting room (no/wrong data)
12:04:28.587  Consumer 5 enters waiting room (no/wrong data)
12:04:28.587  Consumer 4 enters waiting room (no/wrong data)
12:04:28.587  Consumer 3 enters waiting room (no/wrong data)
12:04:28.587  Consumer 2 enters waiting room (no/wrong data)
12:04:28.765  Supplier 4 produces data 3
12:04:28.765  Supplier 4 enters meeting room
12:04:28.766  Supplier 4 leaves data 3 for consumer 4
12:04:28.774  Supplier 4 sleeps 82ms
12:04:28.774  Consumer 1 enters waiting room (no/wrong data)
12:04:28.775  Consumer 2 enters waiting room (no/wrong data)
12:04:28.777  Consumer 3 enters waiting room (no/wrong data)
12:04:28.777  Consumer 4 enters meeting room
12:04:28.777  Consumer 4 removes data 3
12:04:28.778  Consumer 4 sleeps 795ms
12:04:28.780  Consumer 5 enters waiting room (no/wrong data)
12:04:28.780  Consumer 6 enters waiting room (no/wrong data)
12:04:28.780  Consumer 7 enters waiting room (no/wrong data)
12:04:28.782  Consumer 8 enters waiting room (no/wrong data)
12:04:28.782  Consumer 9 enters waiting room (no/wrong data)
12:04:28.783  Consumer 10 enters waiting room (no/wrong data)
12:04:28.783  Consumer 3 enters waiting room (no/wrong data)
12:04:28.783  Consumer 2 enters waiting room (no/wrong data)
12:04:28.784  Consumer 1 enters waiting room (no/wrong data)
12:04:28.845  Supplier 5 produces data 82
12:04:28.845  Supplier 5 enters meeting room
12:04:28.846  Supplier 5 leaves data 82 for consumer 5
12:04:28.846  Supplier 5 sleeps 440ms
12:04:28.846  Consumer 5 enters meeting room
12:04:28.846  Consumer 5 removes data 82
12:04:28.846  Consumer 5 sleeps 225ms
12:04:28.846  Consumer 1 enters waiting room (no/wrong data)
12:04:28.846  Consumer 2 enters waiting room (no/wrong data)
12:04:28.847  Consumer 3 enters waiting room (no/wrong data)
12:04:28.847  Consumer 10 enters waiting room (no/wrong data)
12:04:28.847  Consumer 9 enters waiting room (no/wrong data)
12:04:28.847  Consumer 8 enters waiting room (no/wrong data)
12:04:28.847  Consumer 7 enters waiting room (no/wrong data)
12:04:28.847  Consumer 6 enters waiting room (no/wrong data)
12:04:28.857  Supplier 4 produces data 65
12:04:28.857  Supplier 4 enters meeting room
12:04:28.858  Supplier 4 leaves data 65 for consumer 4
12:04:28.858  Supplier 4 sleeps 150ms
12:04:28.858  Consumer 1 enters waiting room (no/wrong data)
12:04:28.859  Consumer 6 enters waiting room (no/wrong data)
12:04:28.859  Consumer 7 enters waiting room (no/wrong data)
12:04:28.859  Consumer 8 enters waiting room (no/wrong data)
12:04:28.860  Consumer 9 enters waiting room (no/wrong data)
12:04:28.861  Consumer 10 enters waiting room (no/wrong data)
12:04:28.861  Consumer 3 enters waiting room (no/wrong data)
12:04:28.862  Consumer 2 enters waiting room (no/wrong data)
12:04:29.010  Supplier 4 produces data 43
12:04:29.010  Supplier 4 enters waiting room (data present)
12:04:29.016  Supplier 10 produces data 35
12:04:29.017  Supplier 10 enters waiting room (data present)
12:04:29.031  Supplier 2 produces data 20
12:04:29.031  Supplier 2 enters waiting room (data present)
12:04:29.072  Consumer 5 enters waiting room (no/wrong data)
12:04:29.086  Supplier 6 produces data 19
12:04:29.086  Supplier 6 enters waiting room (data present)
12:04:29.141  Supplier 7 produces data 94
12:04:29.141  Supplier 7 enters waiting room (data present)
12:04:29.227  Supplier 3 produces data 76
12:04:29.227  Supplier 3 enters waiting room (data present)
12:04:29.290  Supplier 5 produces data 75
12:04:29.290  Supplier 5 enters waiting room (data present)
12:04:29.503  Supplier 1 produces data 59
12:04:29.503  Supplier 1 enters waiting room (data present)
12:04:29.544  Supplier 9 produces data 91
12:04:29.544  Supplier 9 enters waiting room (data present)
12:04:29.577  Consumer 4 enters meeting room
12:04:29.577  Consumer 4 removes data 65
12:04:29.577  Consumer 4 sleeps 86ms
12:04:29.577  Consumer 1 enters waiting room (no/wrong data)
12:04:29.577  Supplier 9 enters meeting room
12:04:29.577  Supplier 9 leaves data 91 for consumer 9
12:04:29.578  Supplier 1 enters waiting room (data present)
12:04:29.578  Supplier 9 sleeps 968ms
12:04:29.578  Supplier 5 enters waiting room (data present)
12:04:29.578  Supplier 3 enters waiting room (data present)
12:04:29.578  Supplier 7 enters waiting room (data present)
12:04:29.579  Supplier 6 enters waiting room (data present)
12:04:29.579  Consumer 5 enters waiting room (no/wrong data)
12:04:29.579  Supplier 2 enters waiting room (data present)
12:04:29.579  Supplier 10 enters waiting room (data present)
12:04:29.579  Supplier 4 enters waiting room (data present)
12:04:29.579  Consumer 2 enters waiting room (no/wrong data)
12:04:29.580  Consumer 3 enters waiting room (no/wrong data)
12:04:29.580  Consumer 10 enters waiting room (no/wrong data)
12:04:29.580  Consumer 9 enters meeting room
12:04:29.580  Consumer 9 removes data 91
12:04:29.580  Consumer 9 sleeps 354ms
12:04:29.580  Consumer 8 enters waiting room (no/wrong data)
12:04:29.580  Consumer 7 enters waiting room (no/wrong data)
12:04:29.581  Consumer 6 enters waiting room (no/wrong data)
12:04:29.581  Consumer 10 enters waiting room (no/wrong data)
12:04:29.581  Consumer 3 enters waiting room (no/wrong data)
12:04:29.581  Consumer 2 enters waiting room (no/wrong data)
12:04:29.581  Supplier 4 enters meeting room
12:04:29.582  Supplier 4 leaves data 43 for consumer 4
12:04:29.582  Supplier 4 sleeps 456ms
12:04:29.582  Supplier 10 enters waiting room (data present)
12:04:29.582  Supplier 2 enters waiting room (data present)
12:04:29.582  Consumer 5 enters waiting room (no/wrong data)
12:04:29.583  Supplier 6 enters waiting room (data present)
12:04:29.583  Supplier 8 produces data 43
12:04:29.583  Supplier 7 enters waiting room (data present)
12:04:29.583  Supplier 3 enters waiting room (data present)
12:04:29.584  Supplier 5 enters waiting room (data present)
12:04:29.584  Supplier 1 enters waiting room (data present)
12:04:29.585  Consumer 1 enters waiting room (no/wrong data)
12:04:29.585  Supplier 8 enters waiting room (data present)
12:04:29.585  Consumer 2 enters waiting room (no/wrong data)
12:04:29.586  Consumer 3 enters waiting room (no/wrong data)
12:04:29.586  Consumer 10 enters waiting room (no/wrong data)
12:04:29.586  Consumer 6 enters waiting room (no/wrong data)
12:04:29.586  Consumer 7 enters waiting room (no/wrong data)
12:04:29.586  Consumer 8 enters waiting room (no/wrong data)
12:04:29.669  Consumer 4 enters meeting room
12:04:29.669  Consumer 4 removes data 43
12:04:29.669  Consumer 4 sleeps 777ms
12:04:29.670  Supplier 10 enters meeting room
12:04:29.670  Supplier 10 leaves data 35 for consumer 10
12:04:29.670  Supplier 10 sleeps 146ms
12:04:29.671  Consumer 8 enters waiting room (no/wrong data)
12:04:29.671  Consumer 7 enters waiting room (no/wrong data)
12:04:29.671  Consumer 6 enters waiting room (no/wrong data)
12:04:29.672  Consumer 10 enters meeting room
12:04:29.672  Consumer 10 removes data 35
12:04:29.672  Consumer 10 sleeps 940ms
12:04:29.672  Consumer 3 enters waiting room (no/wrong data)
12:04:29.672  Consumer 2 enters waiting room (no/wrong data)
12:04:29.673  Supplier 8 enters meeting room
12:04:29.673  Supplier 8 leaves data 43 for consumer 8
12:04:29.673  Supplier 8 sleeps 135ms
12:04:29.673  Consumer 1 enters waiting room (no/wrong data)
12:04:29.673  Supplier 1 enters waiting room (data present)
12:04:29.674  Supplier 5 enters waiting room (data present)
12:04:29.674  Supplier 3 enters waiting room (data present)
12:04:29.674  Supplier 7 enters waiting room (data present)
12:04:29.674  Supplier 6 enters waiting room (data present)
12:04:29.674  Consumer 5 enters waiting room (no/wrong data)
12:04:29.675  Supplier 2 enters waiting room (data present)
12:04:29.675  Consumer 2 enters waiting room (no/wrong data)
12:04:29.675  Consumer 3 enters waiting room (no/wrong data)
12:04:29.676  Consumer 6 enters waiting room (no/wrong data)
12:04:29.676  Consumer 7 enters waiting room (no/wrong data)
12:04:29.677  Consumer 8 enters meeting room
12:04:29.678  Consumer 8 removes data 43
12:04:29.678  Consumer 8 sleeps 84ms
12:04:29.678  Consumer 1 enters waiting room (no/wrong data)
12:04:29.678  Consumer 7 enters waiting room (no/wrong data)
12:04:29.678  Consumer 6 enters waiting room (no/wrong data)
12:04:29.679  Consumer 3 enters waiting room (no/wrong data)
12:04:29.679  Consumer 2 enters waiting room (no/wrong data)
12:04:29.679  Supplier 2 enters meeting room
12:04:29.679  Supplier 2 leaves data 20 for consumer 2
12:04:29.679  Supplier 2 sleeps 508ms
12:04:29.679  Consumer 5 enters waiting room (no/wrong data)
12:04:29.679  Supplier 6 enters waiting room (data present)
12:04:29.680  Supplier 7 enters waiting room (data present)
12:04:29.680  Supplier 3 enters waiting room (data present)
12:04:29.680  Supplier 5 enters waiting room (data present)
12:04:29.680  Supplier 1 enters waiting room (data present)
12:04:29.680  Consumer 2 enters meeting room
12:04:29.680  Consumer 2 removes data 20
12:04:29.681  Consumer 3 enters waiting room (no/wrong data)
12:04:29.681  Consumer 2 sleeps 83ms
12:04:29.681  Consumer 6 enters waiting room (no/wrong data)
12:04:29.681  Consumer 7 enters waiting room (no/wrong data)
12:04:29.681  Consumer 1 enters waiting room (no/wrong data)
12:04:29.681  Supplier 1 enters meeting room
12:04:29.682  Supplier 1 leaves data 59 for consumer 1
12:04:29.682  Supplier 1 sleeps 449ms
12:04:29.682  Supplier 5 enters waiting room (data present)
12:04:29.682  Supplier 3 enters waiting room (data present)
12:04:29.683  Supplier 7 enters waiting room (data present)
12:04:29.683  Supplier 6 enters waiting room (data present)
12:04:29.683  Consumer 5 enters waiting room (no/wrong data)
12:04:29.683  Consumer 1 enters meeting room
12:04:29.684  Consumer 1 removes data 59
12:04:29.684  Consumer 1 sleeps 803ms
12:04:29.684  Consumer 7 enters waiting room (no/wrong data)
12:04:29.684  Consumer 6 enters waiting room (no/wrong data)
12:04:29.684  Consumer 3 enters waiting room (no/wrong data)
12:04:29.684  Consumer 5 enters waiting room (no/wrong data)
12:04:29.684  Supplier 6 enters meeting room
12:04:29.684  Supplier 6 leaves data 19 for consumer 6
12:04:29.684  Supplier 6 sleeps 900ms
12:04:29.685  Supplier 7 enters waiting room (data present)
12:04:29.685  Supplier 3 enters waiting room (data present)
12:04:29.685  Supplier 5 enters waiting room (data present)
12:04:29.685  Consumer 5 enters waiting room (no/wrong data)
12:04:29.685  Consumer 3 enters waiting room (no/wrong data)
12:04:29.686  Consumer 6 enters meeting room
12:04:29.686  Consumer 6 removes data 19
12:04:29.686  Consumer 6 sleeps 214ms
12:04:29.686  Consumer 7 enters waiting room (no/wrong data)
12:04:29.687  Consumer 3 enters waiting room (no/wrong data)
12:04:29.687  Consumer 5 enters waiting room (no/wrong data)
12:04:29.687  Supplier 5 enters meeting room
12:04:29.687  Supplier 5 leaves data 75 for consumer 5
12:04:29.687  Supplier 5 sleeps 17ms
12:04:29.687  Supplier 3 enters waiting room (data present)
12:04:29.688  Supplier 7 enters waiting room (data present)
12:04:29.688  Consumer 5 enters meeting room
12:04:29.688  Consumer 5 removes data 75
12:04:29.688  Consumer 5 sleeps 259ms
12:04:29.689  Consumer 3 enters waiting room (no/wrong data)
12:04:29.689  Consumer 7 enters waiting room (no/wrong data)
12:04:29.689  Supplier 7 enters meeting room
12:04:29.689  Supplier 7 leaves data 94 for consumer 7
12:04:29.689  Supplier 7 sleeps 157ms
12:04:29.689  Supplier 3 enters waiting room (data present)
12:04:29.689  Consumer 7 enters meeting room
12:04:29.689  Consumer 7 removes data 94
12:04:29.690  Consumer 7 sleeps 710ms
12:04:29.690  Consumer 3 enters waiting room (no/wrong data)
12:04:29.690  Supplier 3 enters meeting room
12:04:29.690  Supplier 3 leaves data 76 for consumer 3
12:04:29.690  Supplier 3 sleeps 841ms
12:04:29.691  Consumer 3 enters meeting room
12:04:29.691  Consumer 3 removes data 76
12:04:29.691  Consumer 3 sleeps 888ms
12:04:29.706  Supplier 5 produces data 96
12:04:29.706  Supplier 5 enters meeting room
12:04:29.706  Supplier 5 leaves data 96 for consumer 5
12:04:29.706  Supplier 5 sleeps 861ms
12:04:29.766  Consumer 8 enters waiting room (no/wrong data)
12:04:29.767  Consumer 2 enters waiting room (no/wrong data)
12:04:29.813  Supplier 8 produces data 69
12:04:29.813  Supplier 8 enters waiting room (data present)
12:04:29.821  Supplier 10 produces data 71
12:04:29.821  Supplier 10 enters waiting room (data present)
12:04:29.851  Supplier 7 produces data 27
12:04:29.851  Supplier 7 enters waiting room (data present)
12:04:29.904  Consumer 6 enters waiting room (no/wrong data)
12:04:29.937  Consumer 9 enters waiting room (no/wrong data)
12:04:29.953  Consumer 5 enters meeting room
12:04:29.953  Consumer 5 removes data 96
12:04:29.953  Consumer 5 sleeps 229ms
12:04:29.954  Consumer 8 enters waiting room (no/wrong data)
12:04:29.954  Consumer 9 enters waiting room (no/wrong data)
12:04:29.954  Consumer 6 enters waiting room (no/wrong data)
12:04:29.954  Supplier 7 enters meeting room
12:04:29.955  Supplier 7 leaves data 27 for consumer 7
12:04:29.955  Supplier 7 sleeps 888ms
12:04:29.955  Supplier 10 enters waiting room (data present)
12:04:29.955  Supplier 8 enters waiting room (data present)
12:04:29.955  Consumer 2 enters waiting room (no/wrong data)
12:04:29.956  Consumer 6 enters waiting room (no/wrong data)
12:04:29.956  Consumer 9 enters waiting room (no/wrong data)
12:04:29.956  Consumer 8 enters waiting room (no/wrong data)
12:04:30.043  Supplier 4 produces data 11
12:04:30.043  Supplier 4 enters waiting room (data present)
12:04:30.134  Supplier 1 produces data 8
12:04:30.134  Supplier 1 enters waiting room (data present)
12:04:30.187  Consumer 5 enters waiting room (no/wrong data)
12:04:30.191  Supplier 2 produces data 64
12:04:30.191  Supplier 2 enters waiting room (data present)
12:04:30.403  Consumer 7 enters meeting room
12:04:30.403  Consumer 7 removes data 27
12:04:30.403  Consumer 7 sleeps 75ms
12:04:30.403  Supplier 10 enters meeting room
12:04:30.403  Supplier 10 leaves data 71 for consumer 10
12:04:30.404  Supplier 10 sleeps 356ms
12:04:30.404  Supplier 2 enters waiting room (data present)
12:04:30.404  Consumer 5 enters waiting room (no/wrong data)
12:04:30.404  Supplier 1 enters waiting room (data present)
12:04:30.404  Supplier 4 enters waiting room (data present)
12:04:30.404  Consumer 8 enters waiting room (no/wrong data)
12:04:30.405  Consumer 9 enters waiting room (no/wrong data)
12:04:30.405  Consumer 6 enters waiting room (no/wrong data)
12:04:30.405  Consumer 2 enters waiting room (no/wrong data)
12:04:30.405  Supplier 8 enters waiting room (data present)
12:04:30.451  Consumer 4 enters waiting room (no/wrong data)
12:04:30.483  Consumer 7 enters waiting room (no/wrong data)
12:04:30.490  Consumer 1 enters waiting room (no/wrong data)
12:04:30.535  Supplier 3 produces data 26
12:04:30.535  Supplier 3 enters waiting room (data present)
12:04:30.551  Supplier 9 produces data 14
12:04:30.551  Supplier 9 enters waiting room (data present)
12:04:30.572  Supplier 5 produces data 92
12:04:30.572  Supplier 5 enters waiting room (data present)
12:04:30.583  Consumer 3 enters waiting room (no/wrong data)
12:04:30.589  Supplier 6 produces data 97
12:04:30.589  Supplier 6 enters waiting room (data present)
12:04:30.616  Consumer 10 enters meeting room
12:04:30.616  Consumer 10 removes data 71
12:04:30.617  Consumer 10 sleeps 951ms
12:04:30.617  Supplier 2 enters meeting room
12:04:30.617  Supplier 2 leaves data 64 for consumer 2
12:04:30.617  Supplier 2 sleeps 891ms
12:04:30.617  Supplier 6 enters waiting room (data present)
12:04:30.617  Consumer 3 enters waiting room (no/wrong data)
12:04:30.617  Supplier 5 enters waiting room (data present)
12:04:30.617  Supplier 9 enters waiting room (data present)
12:04:30.618  Supplier 3 enters waiting room (data present)
12:04:30.618  Consumer 1 enters waiting room (no/wrong data)
12:04:30.618  Consumer 7 enters waiting room (no/wrong data)
12:04:30.618  Consumer 4 enters waiting room (no/wrong data)
12:04:30.618  Supplier 8 enters waiting room (data present)
12:04:30.618  Consumer 2 enters meeting room
12:04:30.618  Consumer 2 removes data 64
12:04:30.618  Consumer 2 sleeps 185ms
12:04:30.619  Consumer 6 enters waiting room (no/wrong data)
12:04:30.619  Consumer 9 enters waiting room (no/wrong data)
12:04:30.619  Consumer 8 enters waiting room (no/wrong data)
12:04:30.619  Supplier 4 enters meeting room
12:04:30.619  Supplier 4 leaves data 11 for consumer 4
12:04:30.619  Supplier 4 sleeps 630ms
12:04:30.619  Supplier 1 enters waiting room (data present)
12:04:30.619  Consumer 5 enters waiting room (no/wrong data)
12:04:30.619  Consumer 8 enters waiting room (no/wrong data)
12:04:30.620  Consumer 9 enters waiting room (no/wrong data)
12:04:30.620  Consumer 6 enters waiting room (no/wrong data)
12:04:30.620  Supplier 8 enters waiting room (data present)
12:04:30.620  Consumer 4 enters meeting room
12:04:30.620  Consumer 4 removes data 11
12:04:30.620  Consumer 4 sleeps 705ms
12:04:30.620  Consumer 7 enters waiting room (no/wrong data)
12:04:30.620  Consumer 1 enters waiting room (no/wrong data)
12:04:30.620  Supplier 3 enters meeting room
12:04:30.620  Supplier 3 leaves data 26 for consumer 3
12:04:30.620  Supplier 3 sleeps 417ms
12:04:30.621  Supplier 9 enters waiting room (data present)
12:04:30.621  Supplier 5 enters waiting room (data present)
12:04:30.621  Consumer 3 enters meeting room
12:04:30.621  Consumer 3 removes data 26
12:04:30.621  Consumer 3 sleeps 932ms
12:04:30.622  Supplier 6 enters meeting room
12:04:30.622  Supplier 6 leaves data 97 for consumer 6
12:04:30.622  Supplier 6 sleeps 921ms
12:04:30.622  Supplier 5 enters waiting room (data present)
12:04:30.622  Supplier 9 enters waiting room (data present)
12:04:30.622  Consumer 1 enters waiting room (no/wrong data)
12:04:30.623  Consumer 7 enters waiting room (no/wrong data)
12:04:30.623  Supplier 8 enters waiting room (data present)
12:04:30.623  Consumer 6 enters meeting room
12:04:30.624  Consumer 6 removes data 97
12:04:30.624  Consumer 6 sleeps 422ms
12:04:30.624  Consumer 9 enters waiting room (no/wrong data)
12:04:30.624  Consumer 8 enters waiting room (no/wrong data)
12:04:30.624  Consumer 5 enters waiting room (no/wrong data)
12:04:30.624  Supplier 1 enters meeting room
12:04:30.625  Supplier 1 leaves data 8 for consumer 1
12:04:30.625  Consumer 9 enters waiting room (no/wrong data)
12:04:30.625  Supplier 1 sleeps 142ms
12:04:30.625  Consumer 5 enters waiting room (no/wrong data)
12:04:30.625  Consumer 8 enters waiting room (no/wrong data)
12:04:30.625  Supplier 8 enters waiting room (data present)
12:04:30.625  Consumer 7 enters waiting room (no/wrong data)
12:04:30.625  Consumer 1 enters meeting room
12:04:30.626  Consumer 1 removes data 8
12:04:30.626  Consumer 1 sleeps 77ms
12:04:30.626  Supplier 9 enters meeting room
12:04:30.626  Supplier 9 leaves data 14 for consumer 9
12:04:30.626  Supplier 5 enters waiting room (data present)
12:04:30.626  Supplier 9 sleeps 617ms
12:04:30.626  Consumer 7 enters waiting room (no/wrong data)
12:04:30.627  Supplier 8 enters waiting room (data present)
12:04:30.627  Consumer 8 enters waiting room (no/wrong data)
12:04:30.627  Consumer 5 enters waiting room (no/wrong data)
12:04:30.627  Consumer 9 enters meeting room
12:04:30.627  Consumer 9 removes data 14
12:04:30.627  Consumer 9 sleeps 972ms
12:04:30.627  Supplier 5 enters meeting room
12:04:30.627  Supplier 5 leaves data 92 for consumer 5
12:04:30.627  Supplier 5 sleeps 204ms
12:04:30.627  Consumer 5 enters meeting room
12:04:30.628  Consumer 5 removes data 92
12:04:30.628  Consumer 5 sleeps 377ms
12:04:30.628  Consumer 8 enters waiting room (no/wrong data)
12:04:30.628  Supplier 8 enters meeting room
12:04:30.628  Supplier 8 leaves data 69 for consumer 8
12:04:30.628  Supplier 8 sleeps 224ms
12:04:30.628  Consumer 7 enters waiting room (no/wrong data)
12:04:30.628  Consumer 8 enters meeting room
12:04:30.628  Consumer 8 removes data 69
12:04:30.628  Consumer 8 sleeps 956ms
12:04:30.628  Consumer 7 enters waiting room (no/wrong data)
12:04:30.706  Consumer 1 enters waiting room (no/wrong data)
12:04:30.763  Supplier 10 produces data 78
12:04:30.763  Supplier 10 enters meeting room
12:04:30.763  Supplier 10 leaves data 78 for consumer 10
12:04:30.763  Supplier 10 sleeps 373ms
12:04:30.763  Consumer 7 enters waiting room (no/wrong data)
12:04:30.763  Consumer 1 enters waiting room (no/wrong data)
12:04:30.769  Supplier 1 produces data 62
12:04:30.769  Supplier 1 enters waiting room (data present)
12:04:30.807  Consumer 2 enters waiting room (no/wrong data)
12:04:30.835  Supplier 5 produces data 43
12:04:30.835  Supplier 5 enters waiting room (data present)
12:04:30.843  Supplier 7 produces data 73
12:04:30.843  Supplier 7 enters waiting room (data present)
12:04:30.855  Supplier 8 produces data 28
12:04:30.855  Supplier 8 enters waiting room (data present)
12:04:31.010  Consumer 5 enters waiting room (no/wrong data)
12:04:31.043  Supplier 3 produces data 2
12:04:31.043  Supplier 3 enters waiting room (data present)
12:04:31.050  Consumer 6 enters waiting room (no/wrong data)
12:04:31.140  Supplier 10 produces data 45
12:04:31.140  Supplier 10 enters waiting room (data present)
12:04:31.248  Supplier 9 produces data 21
12:04:31.248  Supplier 9 enters waiting room (data present)
12:04:31.253  Supplier 4 produces data 8
12:04:31.253  Supplier 4 enters waiting room (data present)
12:04:31.329  Consumer 4 enters waiting room (no/wrong data)
12:04:31.513  Supplier 2 produces data 31
12:04:31.513  Supplier 2 enters waiting room (data present)
12:04:31.548  Supplier 6 produces data 39
12:04:31.548  Supplier 6 enters waiting room (data present)
12:04:31.557  Consumer 3 enters waiting room (no/wrong data)
12:04:31.573  Consumer 10 enters meeting room
12:04:31.573  Consumer 10 removes data 78
12:04:31.573  Consumer 10 sleeps 452ms
12:04:31.573  Consumer 7 enters waiting room (no/wrong data)
12:04:31.574  Consumer 3 enters waiting room (no/wrong data)
12:04:31.574  Supplier 6 enters meeting room
12:04:31.574  Supplier 6 leaves data 39 for consumer 6
12:04:31.574  Supplier 6 sleeps 140ms
12:04:31.574  Supplier 2 enters waiting room (data present)
12:04:31.574  Consumer 4 enters waiting room (no/wrong data)
12:04:31.575  Supplier 4 enters waiting room (data present)
12:04:31.575  Supplier 9 enters waiting room (data present)
12:04:31.575  Supplier 10 enters waiting room (data present)
12:04:31.575  Consumer 6 enters meeting room
12:04:31.575  Consumer 6 removes data 39
12:04:31.575  Consumer 6 sleeps 264ms
12:04:31.575  Supplier 3 enters meeting room
12:04:31.575  Supplier 3 leaves data 2 for consumer 3
12:04:31.575  Supplier 3 sleeps 261ms
12:04:31.575  Consumer 5 enters waiting room (no/wrong data)
12:04:31.576  Supplier 8 enters waiting room (data present)
12:04:31.576  Supplier 7 enters waiting room (data present)
12:04:31.576  Supplier 5 enters waiting room (data present)
12:04:31.576  Consumer 2 enters waiting room (no/wrong data)
12:04:31.576  Supplier 1 enters waiting room (data present)
12:04:31.576  Consumer 1 enters waiting room (no/wrong data)
12:04:31.577  Supplier 10 enters waiting room (data present)
12:04:31.577  Supplier 9 enters waiting room (data present)
12:04:31.577  Supplier 4 enters waiting room (data present)
12:04:31.577  Consumer 4 enters waiting room (no/wrong data)
12:04:31.577  Supplier 2 enters waiting room (data present)
12:04:31.578  Consumer 3 enters meeting room
12:04:31.578  Consumer 3 removes data 2
12:04:31.578  Consumer 3 sleeps 854ms
12:04:31.578  Consumer 7 enters waiting room (no/wrong data)
12:04:31.579  Supplier 2 enters meeting room
12:04:31.579  Supplier 2 leaves data 31 for consumer 2
12:04:31.579  Supplier 2 sleeps 357ms
12:04:31.579  Consumer 4 enters waiting room (no/wrong data)
12:04:31.579  Supplier 4 enters waiting room (data present)
12:04:31.579  Supplier 9 enters waiting room (data present)
12:04:31.579  Supplier 10 enters waiting room (data present)
12:04:31.580  Consumer 1 enters waiting room (no/wrong data)
12:04:31.580  Supplier 1 enters waiting room (data present)
12:04:31.580  Consumer 2 enters meeting room
12:04:31.580  Consumer 2 removes data 31
12:04:31.580  Consumer 2 sleeps 981ms
12:04:31.580  Supplier 5 enters meeting room
12:04:31.580  Supplier 5 leaves data 43 for consumer 5
12:04:31.580  Supplier 5 sleeps 134ms
12:04:31.580  Supplier 7 enters waiting room (data present)
12:04:31.580  Supplier 8 enters waiting room (data present)
12:04:31.581  Consumer 5 enters meeting room
12:04:31.581  Consumer 5 removes data 43
12:04:31.581  Consumer 5 sleeps 890ms
12:04:31.581  Supplier 7 enters meeting room
12:04:31.581  Supplier 7 leaves data 73 for consumer 7
12:04:31.581  Supplier 7 sleeps 754ms
12:04:31.581  Supplier 8 enters waiting room (data present)
12:04:31.581  Supplier 1 enters waiting room (data present)
12:04:31.581  Consumer 1 enters waiting room (no/wrong data)
12:04:31.581  Supplier 10 enters waiting room (data present)
12:04:31.581  Supplier 9 enters waiting room (data present)
12:04:31.581  Supplier 4 enters waiting room (data present)
12:04:31.582  Consumer 4 enters waiting room (no/wrong data)
12:04:31.582  Consumer 7 enters meeting room
12:04:31.582  Consumer 7 removes data 73
12:04:31.582  Consumer 7 sleeps 513ms
12:04:31.582  Supplier 8 enters meeting room
12:04:31.582  Supplier 8 leaves data 28 for consumer 8
12:04:31.582  Supplier 8 sleeps 354ms
12:04:31.582  Consumer 4 enters waiting room (no/wrong data)
12:04:31.582  Supplier 4 enters waiting room (data present)
12:04:31.582  Supplier 9 enters waiting room (data present)
12:04:31.582  Supplier 10 enters waiting room (data present)
12:04:31.582  Consumer 1 enters waiting room (no/wrong data)
12:04:31.582  Supplier 1 enters waiting room (data present)
12:04:31.587  Consumer 8 enters meeting room
12:04:31.587  Consumer 8 removes data 28
12:04:31.587  Consumer 8 sleeps 637ms
12:04:31.588  Consumer 4 enters waiting room (no/wrong data)
12:04:31.588  Supplier 1 enters meeting room
12:04:31.588  Supplier 1 leaves data 62 for consumer 1
12:04:31.588  Consumer 1 enters meeting room
12:04:31.588  Supplier 1 sleeps 748ms
12:04:31.589  Consumer 1 removes data 62
12:04:31.589  Supplier 10 enters meeting room
12:04:31.589  Consumer 1 sleeps 447ms
12:04:31.589  Supplier 10 leaves data 45 for consumer 10
12:04:31.589  Supplier 10 sleeps 192ms
12:04:31.590  Supplier 9 enters waiting room (data present)
12:04:31.590  Supplier 4 enters waiting room (data present)
12:04:31.590  Consumer 4 enters waiting room (no/wrong data)
12:04:31.603  Consumer 9 enters waiting room (no/wrong data)
12:04:31.716  Supplier 6 produces data 33
12:04:31.716  Supplier 5 produces data 40
12:04:31.717  Supplier 6 enters waiting room (data present)
12:04:31.717  Supplier 5 enters waiting room (data present)
12:04:31.787  Supplier 10 produces data 42
12:04:31.787  Supplier 10 enters waiting room (data present)
12:04:31.840  Supplier 3 produces data 69
12:04:31.841  Supplier 3 enters waiting room (data present)
12:04:31.843  Consumer 6 enters waiting room (no/wrong data)
12:04:31.940  Supplier 2 produces data 99
12:04:31.940  Supplier 8 produces data 37
12:04:31.941  Supplier 2 enters waiting room (data present)
12:04:31.941  Supplier 8 enters waiting room (data present)
12:04:32.027  Consumer 10 enters meeting room
12:04:32.027  Consumer 10 removes data 45
12:04:32.027  Consumer 10 sleeps 330ms
12:04:32.027  Supplier 9 enters meeting room
12:04:32.027  Supplier 9 leaves data 21 for consumer 9
12:04:32.027  Supplier 9 sleeps 33ms
12:04:32.027  Supplier 8 enters waiting room (data present)
12:04:32.027  Supplier 2 enters waiting room (data present)
12:04:32.027  Consumer 6 enters waiting room (no/wrong data)
12:04:32.027  Supplier 3 enters waiting room (data present)
12:04:32.027  Supplier 10 enters waiting room (data present)
12:04:32.028  Supplier 5 enters waiting room (data present)
12:04:32.028  Supplier 6 enters waiting room (data present)
12:04:32.028  Consumer 9 enters meeting room
12:04:32.028  Consumer 9 removes data 21
12:04:32.028  Consumer 9 sleeps 691ms
12:04:32.028  Consumer 4 enters waiting room (no/wrong data)
12:04:32.028  Supplier 4 enters meeting room
12:04:32.028  Supplier 4 leaves data 8 for consumer 4
12:04:32.028  Supplier 4 sleeps 723ms
12:04:32.028  Consumer 4 enters meeting room
12:04:32.028  Consumer 4 removes data 8
12:04:32.028  Consumer 4 sleeps 652ms
12:04:32.028  Supplier 6 enters meeting room
12:04:32.029  Supplier 6 leaves data 33 for consumer 6
12:04:32.029  Supplier 6 sleeps 109ms
12:04:32.029  Supplier 5 enters waiting room (data present)
12:04:32.029  Supplier 10 enters waiting room (data present)
12:04:32.029  Supplier 3 enters waiting room (data present)
12:04:32.029  Consumer 6 enters meeting room
12:04:32.029  Consumer 6 removes data 33
12:04:32.029  Consumer 6 sleeps 190ms
12:04:32.029  Supplier 2 enters meeting room
12:04:32.029  Supplier 2 leaves data 99 for consumer 2
12:04:32.029  Supplier 2 sleeps 392ms
12:04:32.029  Supplier 8 enters waiting room (data present)
12:04:32.030  Supplier 3 enters waiting room (data present)
12:04:32.030  Supplier 10 enters waiting room (data present)
12:04:32.030  Supplier 5 enters waiting room (data present)
12:04:32.040  Consumer 1 enters waiting room (no/wrong data)
12:04:32.063  Supplier 9 produces data 15
12:04:32.063  Supplier 9 enters waiting room (data present)
12:04:32.099  Consumer 7 enters waiting room (no/wrong data)
12:04:32.138  Supplier 6 produces data 93
12:04:32.138  Supplier 6 enters waiting room (data present)
12:04:32.223  Consumer 6 enters waiting room (no/wrong data)
12:04:32.227  Consumer 8 enters waiting room (no/wrong data)
12:04:32.340  Supplier 7 produces data 66
12:04:32.340  Supplier 7 enters waiting room (data present)
12:04:32.341  Supplier 1 produces data 91
12:04:32.341  Supplier 1 enters waiting room (data present)
12:04:32.361  Consumer 10 enters waiting room (no/wrong data)
12:04:32.427  Supplier 2 produces data 100
12:04:32.427  Supplier 2 enters waiting room (data present)
12:04:32.433  Consumer 3 enters waiting room (no/wrong data)
12:04:32.474  Consumer 5 enters waiting room (no/wrong data)
12:04:32.566  Consumer 2 enters meeting room
12:04:32.567  Consumer 2 removes data 99
12:04:32.567  Consumer 2 sleeps 15ms
12:04:32.567  Supplier 8 enters meeting room
12:04:32.567  Supplier 8 leaves data 37 for consumer 8
12:04:32.567  Supplier 8 sleeps 905ms
12:04:32.567  Consumer 5 enters waiting room (no/wrong data)
12:04:32.567  Consumer 3 enters waiting room (no/wrong data)
12:04:32.567  Supplier 2 enters waiting room (data present)
12:04:32.568  Consumer 10 enters waiting room (no/wrong data)
12:04:32.568  Supplier 1 enters waiting room (data present)
12:04:32.568  Supplier 7 enters waiting room (data present)
12:04:32.568  Consumer 8 enters meeting room
12:04:32.568  Consumer 8 removes data 37
12:04:32.568  Consumer 8 sleeps 78ms
12:04:32.568  Consumer 6 enters waiting room (no/wrong data)
12:04:32.568  Supplier 6 enters meeting room
12:04:32.569  Supplier 6 leaves data 93 for consumer 6
12:04:32.569  Supplier 6 sleeps 351ms
12:04:32.569  Consumer 7 enters waiting room (no/wrong data)
12:04:32.569  Supplier 9 enters waiting room (data present)
12:04:32.569  Consumer 1 enters waiting room (no/wrong data)
12:04:32.569  Supplier 5 enters waiting room (data present)
12:04:32.569  Supplier 10 enters waiting room (data present)
12:04:32.569  Supplier 3 enters waiting room (data present)
12:04:32.569  Consumer 6 enters meeting room
12:04:32.569  Consumer 6 removes data 93
12:04:32.569  Consumer 6 sleeps 39ms
12:04:32.569  Supplier 7 enters meeting room
12:04:32.569  Supplier 7 leaves data 66 for consumer 7
12:04:32.570  Supplier 7 sleeps 364ms
12:04:32.570  Supplier 1 enters waiting room (data present)
12:04:32.570  Consumer 10 enters waiting room (no/wrong data)
12:04:32.570  Supplier 2 enters waiting room (data present)
12:04:32.570  Consumer 3 enters waiting room (no/wrong data)
12:04:32.570  Consumer 5 enters waiting room (no/wrong data)
12:04:32.570  Supplier 3 enters waiting room (data present)
12:04:32.570  Supplier 10 enters waiting room (data present)
12:04:32.570  Supplier 5 enters waiting room (data present)
12:04:32.570  Consumer 1 enters waiting room (no/wrong data)
12:04:32.570  Supplier 9 enters waiting room (data present)
12:04:32.570  Consumer 7 enters meeting room
12:04:32.570  Consumer 7 removes data 66
12:04:32.571  Consumer 7 sleeps 804ms
12:04:32.571  Supplier 1 enters meeting room
12:04:32.571  Supplier 1 leaves data 91 for consumer 1
12:04:32.571  Supplier 1 sleeps 294ms
12:04:32.571  Supplier 9 enters waiting room (data present)
12:04:32.571  Consumer 1 enters meeting room
12:04:32.571  Consumer 1 removes data 91
12:04:32.571  Consumer 1 sleeps 280ms
12:04:32.572  Supplier 5 enters meeting room
12:04:32.572  Supplier 5 leaves data 40 for consumer 5
12:04:32.572  Supplier 10 enters waiting room (data present)
12:04:32.572  Supplier 5 sleeps 445ms
12:04:32.572  Supplier 3 enters waiting room (data present)
12:04:32.572  Consumer 5 enters meeting room
12:04:32.572  Consumer 5 removes data 40
12:04:32.572  Consumer 5 sleeps 868ms
12:04:32.573  Consumer 3 enters waiting room (no/wrong data)
12:04:32.573  Supplier 2 enters meeting room
12:04:32.573  Supplier 2 leaves data 100 for consumer 2
12:04:32.573  Supplier 2 sleeps 758ms
12:04:32.573  Consumer 10 enters waiting room (no/wrong data)
12:04:32.574  Consumer 3 enters waiting room (no/wrong data)
12:04:32.574  Supplier 3 enters waiting room (data present)
12:04:32.574  Supplier 10 enters waiting room (data present)
12:04:32.575  Supplier 9 enters waiting room (data present)
12:04:32.582  Consumer 2 enters meeting room
12:04:32.583  Consumer 2 removes data 100
12:04:32.583  Consumer 2 sleeps 731ms
12:04:32.583  Consumer 10 enters waiting room (no/wrong data)
12:04:32.583  Supplier 9 enters meeting room
12:04:32.583  Supplier 9 leaves data 15 for consumer 9
12:04:32.583  Supplier 9 sleeps 822ms
12:04:32.583  Supplier 10 enters waiting room (data present)
12:04:32.583  Supplier 3 enters waiting room (data present)
12:04:32.583  Consumer 3 enters waiting room (no/wrong data)
12:04:32.583  Consumer 10 enters waiting room (no/wrong data)
12:04:32.613  Consumer 6 enters waiting room (no/wrong data)
12:04:32.650  Consumer 8 enters waiting room (no/wrong data)
12:04:32.683  Consumer 4 enters waiting room (no/wrong data)
12:04:32.723  Consumer 9 enters meeting room
12:04:32.724  Consumer 9 removes data 15
12:04:32.724  Consumer 9 sleeps 197ms
12:04:32.724  Supplier 10 enters meeting room
12:04:32.724  Supplier 10 leaves data 42 for consumer 10
12:04:32.724  Consumer 4 enters waiting room (no/wrong data)
12:04:32.724  Supplier 10 sleeps 254ms
12:04:32.724  Consumer 8 enters waiting room (no/wrong data)
12:04:32.724  Consumer 6 enters waiting room (no/wrong data)
12:04:32.724  Consumer 10 enters meeting room
12:04:32.724  Consumer 10 removes data 42
12:04:32.724  Consumer 10 sleeps 736ms
12:04:32.725  Consumer 3 enters waiting room (no/wrong data)
12:04:32.725  Supplier 3 enters meeting room
12:04:32.725  Supplier 3 leaves data 69 for consumer 3
12:04:32.725  Consumer 3 enters meeting room
12:04:32.725  Supplier 3 sleeps 581ms
12:04:32.725  Consumer 3 removes data 69
12:04:32.725  Consumer 3 sleeps 785ms
12:04:32.725  Consumer 6 enters waiting room (no/wrong data)
12:04:32.725  Consumer 8 enters waiting room (no/wrong data)
12:04:32.725  Consumer 4 enters waiting room (no/wrong data)
12:04:32.754  Supplier 4 produces data 92
12:04:32.754  Supplier 4 enters meeting room
12:04:32.754  Supplier 4 leaves data 92 for consumer 4
12:04:32.754  Supplier 4 sleeps 396ms
12:04:32.754  Consumer 6 enters waiting room (no/wrong data)
12:04:32.754  Consumer 4 enters meeting room
12:04:32.755  Consumer 4 removes data 92
12:04:32.755  Consumer 4 sleeps 631ms
12:04:32.755  Consumer 8 enters waiting room (no/wrong data)
12:04:32.755  Consumer 6 enters waiting room (no/wrong data)
12:04:32.855  Consumer 1 enters waiting room (no/wrong data)
12:04:32.869  Supplier 1 produces data 39
12:04:32.869  Supplier 1 enters meeting room
12:04:32.869  Supplier 1 leaves data 39 for consumer 1
12:04:32.869  Supplier 1 sleeps 126ms
12:04:32.869  Consumer 8 enters waiting room (no/wrong data)
12:04:32.869  Consumer 1 enters meeting room
12:04:32.869  Consumer 1 removes data 39
12:04:32.869  Consumer 1 sleeps 144ms
12:04:32.869  Consumer 6 enters waiting room (no/wrong data)
12:04:32.869  Consumer 8 enters waiting room (no/wrong data)
12:04:32.924  Supplier 6 produces data 81
12:04:32.924  Consumer 9 enters waiting room (no/wrong data)
12:04:32.924  Supplier 6 enters meeting room
12:04:32.924  Supplier 6 leaves data 81 for consumer 6
12:04:32.924  Supplier 6 sleeps 306ms
12:04:32.924  Consumer 6 enters meeting room
12:04:32.924  Consumer 6 removes data 81
12:04:32.924  Consumer 6 sleeps 750ms
12:04:32.924  Consumer 9 enters waiting room (no/wrong data)
12:04:32.924  Consumer 8 enters waiting room (no/wrong data)
12:04:32.938  Supplier 7 produces data 83
12:04:32.938  Supplier 7 enters meeting room
12:04:32.938  Supplier 7 leaves data 83 for consumer 7
12:04:32.939  Supplier 7 sleeps 505ms
12:04:32.939  Consumer 9 enters waiting room (no/wrong data)
12:04:32.939  Consumer 8 enters waiting room (no/wrong data)
12:04:32.982  Supplier 10 produces data 70
12:04:32.983  Supplier 10 enters waiting room (data present)
12:04:32.999  Supplier 1 produces data 71
12:04:32.999  Supplier 1 enters waiting room (data present)
12:04:33.016  Consumer 1 enters waiting room (no/wrong data)
12:04:33.020  Supplier 5 produces data 80
12:04:33.021  Supplier 5 enters waiting room (data present)
12:04:33.155  Supplier 4 produces data 12
12:04:33.155  Supplier 4 enters waiting room (data present)
12:04:33.235  Supplier 6 produces data 88
12:04:33.235  Supplier 6 enters waiting room (data present)
12:04:33.310  Supplier 3 produces data 34
12:04:33.311  Supplier 3 enters waiting room (data present)
12:04:33.319  Consumer 2 enters waiting room (no/wrong data)
12:04:33.334  Supplier 2 produces data 95
12:04:33.334  Supplier 2 enters waiting room (data present)
12:04:33.379  Consumer 7 enters meeting room
12:04:33.379  Consumer 7 removes data 83
12:04:33.379  Consumer 9 enters waiting room (no/wrong data)
12:04:33.379  Consumer 7 sleeps 373ms
12:04:33.379  Supplier 2 enters meeting room
12:04:33.379  Supplier 2 leaves data 95 for consumer 2
12:04:33.379  Supplier 2 sleeps 56ms
12:04:33.379  Consumer 2 enters meeting room
12:04:33.379  Consumer 2 removes data 95
12:04:33.380  Consumer 2 sleeps 606ms
12:04:33.380  Supplier 3 enters meeting room
12:04:33.380  Supplier 3 leaves data 34 for consumer 3
12:04:33.380  Supplier 3 sleeps 864ms
12:04:33.380  Supplier 6 enters waiting room (data present)
12:04:33.380  Supplier 4 enters waiting room (data present)
12:04:33.380  Supplier 5 enters waiting room (data present)
12:04:33.380  Consumer 1 enters waiting room (no/wrong data)
12:04:33.380  Supplier 1 enters waiting room (data present)
12:04:33.380  Supplier 10 enters waiting room (data present)
12:04:33.380  Consumer 8 enters waiting room (no/wrong data)
12:04:33.381  Consumer 9 enters waiting room (no/wrong data)
12:04:33.391  Consumer 4 enters waiting room (no/wrong data)
12:04:33.411  Supplier 9 produces data 59
12:04:33.411  Supplier 9 enters waiting room (data present)
12:04:33.444  Supplier 2 produces data 44
12:04:33.444  Supplier 2 enters waiting room (data present)
12:04:33.445  Consumer 5 enters waiting room (no/wrong data)
12:04:33.445  Supplier 7 produces data 21
12:04:33.446  Supplier 7 enters waiting room (data present)
12:04:33.465  Consumer 10 enters waiting room (no/wrong data)
12:04:33.476  Supplier 8 produces data 38
12:04:33.477  Supplier 8 enters waiting room (data present)
Consumer 10 interrupted
Consumer 1 interrupted
Supplier 8 interrupted
Consumer 4 interrupted
Consumer 5 interrupted
Supplier 7 interrupted
Consumer 8 interrupted
Supplier 2 interrupted
Supplier 4 interrupted
Supplier 10 interrupted
Consumer 9 interrupted
Supplier 9 interrupted
Supplier 1 interrupted
Supplier 6 interrupted
Supplier 5 interrupted
Consumer 3 interrupted
Supplier 3 interrupted
Consumer 7 interrupted
Consumer 2 interrupted
Consumer 6 interrupted
 */