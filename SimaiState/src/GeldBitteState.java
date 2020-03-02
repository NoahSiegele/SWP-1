
public class GeldBitteState implements IState {

	@Override
	public void goNext(Context c) {
		if(!c.isGeld()) {
			c.setState(new KeinGeldState());
			c.setGeld(true);
		}
		else {
			c.setState(new ZahlenState());
		}
	}

	@Override
	public void printMsg() {
		System.out.println("4 �URO 20 bitte");

	}

}
