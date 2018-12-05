package test;

public class PerformanceTestUtil<T> {

    public long totalSize = 0;

    private T[] datas; // 测试数据

    public PerformanceTestUtil(T[] datas) {
        this.datas = datas;
    }

    public interface Target<E> {
        int doTask(E data) throws Exception;
    }

    public void execute(Target<T> target) throws Exception {
        long start = System.currentTimeMillis();
        for (int i = 0; i < datas.length; i++) {
            totalSize += target.doTask(datas[i]);
        }
        long end = System.currentTimeMillis();
        System.out.println("执行时间：" + (end - start) + "ms");
        System.out.println("总字节数：" + totalSize + "字节");
    }

}
