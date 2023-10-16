import org.apache.pinot.common.version.PinotVersion;


public class Harness {

    public static void main(String[] args) {
        System.out.println(PinotVersion.VERSION);
        System.out.println(PinotVersion.VERSION_METRIC_NAME);
    }
}
