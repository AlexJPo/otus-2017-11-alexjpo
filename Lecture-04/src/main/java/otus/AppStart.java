package otus;

import javax.management.*;
import java.lang.management.ManagementFactory;

/*

-Xms512m
-Xmx512m
-XX:+UseSerialGC or (-XX:+UseParallelGC and -XX:+UseParallelOldGC) or (-XX:+UseParNewGC and -XX:+UseConcMarkSweepGC) or -XX:+UseG1GC
-verbose:gc
-Xloggc:./logs/gc_pid_%p.log
-XX:+PrintGCDateStamps
-XX:+PrintGCDetails
-XX:+UseGCLogFileRotation
-XX:NumberOfGCLogFiles=10
-XX:GCLogFileSize=1M
-Dcom.sun.management.jmxremote.port=15000
-Dcom.sun.management.jmxremote.authenticate=false
-Dcom.sun.management.jmxremote.ssl=false
-XX:+HeapDumpOnOutOfMemoryError
-XX:HeapDumpPath=./dump/

*/
public class AppStart {
    public static void main(String[] args) throws Exception {
        System.out.println("Starting pid: " + ManagementFactory.getRuntimeMXBean().getName());

        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        ObjectName name = new ObjectName("otus:type=Benchmark");
        Benchmark mbean = new Benchmark();

        mbs.registerMBean(mbean, name);
        mbean.run();
    }
}
