public class firstJava{
	public static void main (String[] args) {
		if (args.length == 1) {
			double rndmNum = Math.random();
			System.out.println(rndmNum);
		} else if (args.length == 2) {
			int time = Integer.parseInt(args[0]);
			for (int i = 0; i < time; i++) {
				System.out.println(args[1]);
			}
		} else {
			System.out.println("No arguments given");
		}
	}
}
