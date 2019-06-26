package cn.kevin.enums;

/**
 * @author yongkang.zhang
 * created at 2019-06-26
 */
public enum AbstractEnumTest {

	ZH {
		@Override
		void sayHello() {
			System.out.println("欢迎你");
		}
	},
	EN {
		@Override
		void sayHello() {
			System.out.println("Welcome");
		}
	}
	;

	abstract void sayHello();
}
