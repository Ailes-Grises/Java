public class monster{
	private String name;
	private int level;
	private int hp;
	private int attack;
	private int defense;
	private int speed;

	public monster(){
	}

	public monster(String name, int level, int hp, int attack, int defense, int speed){
		this.name=name;
		this.level=level;
		this.hp=hp;
		this.attack=attack;
		this.defense=defense;
		this.speed=speed;
		System.out.println(this.name + "(" + this.level + " レベル) があらわれた！");
	}

		public monster(monster copy){
			this.name=copy.Name();
			this.level=copy.Level();
			this.hp=copy.Hp();
			this.attack=copy.Attack();
			this.defense=copy.Defense();
			this.speed=copy.Speed();
		System.out.println(this.name + "(" + this.level + " レベル) があらわれた！");
		}

		public String Name(){
			return this.name;
		}
		public int Level(){
			return this.level;
		}
		public int Hp(){
			return this.hp;
		}
		public int Attack(){
			return this.attack;
		}
		public int Defense(){
			return this.defense;
		}
		public int Speed(){
			return this.speed;
		}
		public void kougeki(){
			System.out.println(this.name + "のこうげき！！");
			System.out.println(this.attack + "のダメージ！！");
			this.hp-=this.attack;
		}
}
