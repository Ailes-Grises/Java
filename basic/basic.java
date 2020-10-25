public class basic{
	public static int func(int x){
		return x + 100;
	}
	public static void main(String[] args){
		int x = 1;
		switch(x){
			case 0:
				System.out.println("x : " + x);
				break;
			case 1:
				System.out.println("x : " + x);
				break;
			case 2:
				System.out.println("x : " + x);
				break;
			default:
				System.out.println("x : " + x);
				break;
		}

		// int arr[] = {0, 1, 2, 3, 4};
		int arr[] = new int[5];
		for(int i = 0; i<5; i++){
			arr[i] = i;
			System.out.println("arr[" + i + "] : " + arr[i]);
		}
		System.out.println("Size of arr : " + arr.length);

		// メンバ関数の呼び出し
		// *this は省略可能
		x = func(x);
		System.out.println("x : " + x);

		// function.java のクラスの利用
		int y = 5;
		y = function.Func(y);
		System.out.println("y : " + y);
	}
}
