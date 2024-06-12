public class Wallet {
    private int money;
    private int lost;
    private  int gain;

    public Wallet(int startAmount, int won, int lost){
        this.money = startAmount;
        this.lost = lost;
        this.gain = won;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money, int change) {
        this.money = money + change;
    }

    public int getLost() {
        return lost;
    }

    public void setLost(int lost) {
        this.lost = lost;
    }

    public int getGain() {
        return gain;
    }

    public void setGain(int gain) {
        this.gain = gain;
    }
}
