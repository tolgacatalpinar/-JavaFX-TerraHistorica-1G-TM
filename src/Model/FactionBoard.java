package Model;

public class FactionBoard {

    private int powerIncome;
    private int workerIncome;
    private int priestIncome;
    private int coinIncome;



    public void addCoinIncome(int value)
    {
        coinIncome += value;
    }

    public void addPower(int value)
    {
        powerIncome += value;
    }
    public void addWorker(int value)
    {
        workerIncome += value;
    }





    public int getPowerIncome() {
        return powerIncome;
    }

    public void setPowerIncome(int powerIncome) {
        this.powerIncome = powerIncome;
    }

    public int getWorkerIncome() {
        return workerIncome;
    }

    public void setWorkerIncome(int workerIncome) {
        this.workerIncome = workerIncome;
    }

    public int getPriestIncome() {
        return priestIncome;
    }

    public void setPriestIncome(int priestIncome) {
        this.priestIncome = priestIncome;
    }

    public int getCoinIncome() {
        return coinIncome;
    }

    public void setCoinIncome(int coinIncome) {
        this.coinIncome = coinIncome;
    }
}
