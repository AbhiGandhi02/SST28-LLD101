public class ClassroomController {
    private final DeviceRegistry reg;

    public ClassroomController(DeviceRegistry reg) {
        this.reg = reg;
    }

    public void startClass() {
        PowerButton pj = (PowerButton) reg.getFirstOfType("Projector");
        pj.powerOn();
        ((Connect) pj).connectInput("HDMI-1");

        Brightness lights = (Brightness) reg.getFirstOfType("LightsPanel");
        lights.setBrightness(60);

        Temperature ac = (Temperature) reg.getFirstOfType("AirConditioner");
        ac.setTemperatureC(24);

        Attendance scan = (Attendance) reg.getFirstOfType("AttendanceScanner");
        System.out.println("Attendance scanned: present=" + scan.scanAttendance());
    }

    public void endClass() {
        System.out.println("Shutdown sequence:");
        ((PowerButton) reg.getFirstOfType("Projector")).powerOff();
        ((PowerButton) reg.getFirstOfType("LightsPanel")).powerOff();
        ((PowerButton) reg.getFirstOfType("AirConditioner")).powerOff();
    }
}