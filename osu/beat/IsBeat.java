package osu.beat;

public class IsBeat {

    public double[][] array;

    public IsBeat(double[][] array) {
        this.array = array;


    }

    public int checkAll() {
        int amount = 0;


        for(int i = 0; i < array.length; i++) {
            for(int j = 0; j < 4; j++) {

                amount += Math.abs(array[i][j]);

            }
        }

        //System.out.println(amount);

        return amount;

    }
    public int checkLow() {
        int amount = 0;


        for(int i = 0; i < 30; i++) {
            for(int j = 0; j < 4; j++) {

                amount += Math.abs(array[i][j]);

            }
        }

        //System.out.println(amount);

        return amount;

    }
    public int checkMid() {
        int amount = 0;


        for(int i = 256-15; i < 256+15; i++) {
            for(int j = 0; j < 4; j++) {

                amount += Math.abs(array[i][j]);

            }
        }

        //System.out.println(amount);

        return amount;

    }
    public int checkHigh() {
        int amount = 0;


        for(int i = 512-30; i < 512; i++) {
            for(int j = 0; j < 4; j++) {

                amount += Math.abs(array[i][j]);

            }
        }

        //System.out.println(amount);

        return amount;

    }
}
