package model;

import java.util.concurrent.atomic.AtomicInteger;

public class Product {
    String pid;
    String name;
    volatile int totalCount;
    volatile int blockedCount;

    public Product(String pid, String name, int totalCount) {
        this.pid = pid;
        this.name = name;
        this.totalCount = totalCount;
        blockedCount = 0;
    }

    public String getPid() {
        return pid;
    }


    public int getBlockedCount() {
        return blockedCount;
    }

    public int getPendingInventory() {
        return totalCount - blockedCount;
    }


    public synchronized boolean blockInventory(int count) {
        if(getPendingInventory()<count){
            System.out.println("Not enough inventory!");
            return false;
        }
        blockedCount += count;
        System.out.println(count + " inventory blocked for " + pid);
        System.out.println(this);
        return true;
    }

    public synchronized void confirmInventory(int count) {
        blockedCount -= count;
        totalCount -= count;
        System.out.println(count + " inventory confirmed for " + pid);
        System.out.println(this);
    }

    public synchronized void releaseInventory(int count) {
        blockedCount -= count;
        System.out.println(count + " inventory released of " + pid + " due to order expire");
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "Product{" +
                "pid='" + pid + '\'' +
                ", name='" + name + '\'' +
                ", totalCount=" + totalCount +
                ", blockedCount=" + blockedCount +
                '}';
    }
}
