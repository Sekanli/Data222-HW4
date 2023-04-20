public class homework {
    
    /**
     * @author Sekanli
     * A test class for multiple test cases
     * Making sure no scenario is left unchecked
     */
    public static void main(String[] args)
    {
        SecuritySys system = new SecuritySys();
        MuseumOfficer o1 = new MuseumOfficer("sibelgulmez", "[rac()ecar]", 74);
        MuseumOfficer o2 = new MuseumOfficer("", "[rac()ecar]", 74);
        MuseumOfficer o3 = new MuseumOfficer("sibel1", "[rac()ecar]", 74);
        MuseumOfficer o4 = new MuseumOfficer("sibel", "pass[]", 74);     
        MuseumOfficer o5 = new MuseumOfficer("sibel", "abcdabcd", 74);
        MuseumOfficer o6 = new MuseumOfficer("sibel", "[[[[]]]]", 74);


        MuseumOfficer o7 = new MuseumOfficer("sibel", "[no](no)", 75);
        MuseumOfficer o8 = new MuseumOfficer("sibel", "[rac()ecar]]", 75);
        MuseumOfficer o9 = new MuseumOfficer("sibel", "[rac()ecars]", 75);
        MuseumOfficer o10 = new MuseumOfficer("sibel", "[rac()ecar]", 5);
        MuseumOfficer o11 = new MuseumOfficer("sibel", "[rac()ecar]", 35);
        

        system.SystemCheck(o1);
        system.SystemCheck(o2);
        system.SystemCheck(o3);
        system.SystemCheck(o4);
        system.SystemCheck(o5);
        system.SystemCheck(o6);

        
        System.out.println("CHECKPOINT ------------------ \n");

        system.SystemCheck(o7);
        system.SystemCheck(o8);
        system.SystemCheck(o9);
        system.SystemCheck(o10);
        system.SystemCheck(o11);
     
    }
}
