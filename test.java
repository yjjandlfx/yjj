package test;
import java.util.Arrays;
import java.util.Scanner;



		public class test {
			public static void main(String[] args) {
    //定义数据主体：  菜品
				
				String[] dishName = {"红烧带鱼","红烧茄子","番茄炒蛋","白菜"};//菜品名称
				double[] prices = {38.0,20.0,15.0,10.0};   //菜品单价
				int[] praiseNum = new int[4];     //点赞数
				//定义数据主体：（订单）订餐人  餐品信息  送餐时间 送餐地址 总金额 订单状态
				String []names = new String[4];   //
				String []dishMsg = new String[4];
				int[] times = new int[4];
				String[] adresses = new String[4];
				double []sumPrices = new double [4];
				int [] states = new int[4]; //订单状态：已预订。已完成。
				
   //初始化两个订单信息
				names[0]= "张三";
				dishMsg[0]= "红烧带鱼";
				times[0] = 10;
				adresses[0] = "应技大";
				sumPrices[0] = 76; //餐费>50，免配送费
				states[0] = 1;
				
				names[1]= "李四";
				dishMsg[1]= "红烧带鱼";
				times[1] = 11;
				adresses[1] = "应技大";
				sumPrices[1] = 26;
				states[1] = 1;
				
				
	//搭建项目的整体框架    （主菜单）
				
				Scanner input = new Scanner(System.in);
				int num = -1;//用户输入0时退出系统，用户输入数字进行下步判断
				//记录用户是否退出系统的状态：退出true,  不退出FALSE；
				boolean flag = false;
				System.out.println("欢迎使用*******订餐系统");
				do {
					System.out.println("*****************************************");
					System.out.println("1.我要订餐");
					System.out.println("2.查看餐袋");
					System.out.println("3.签收订单");
					System.out.println("4.删除订单");
					System.out.println("5.我要点赞");
					System.out.println("6.退出系统");
					System.out.println("*****************************************");
					System.out.print("请选择：");
					
					int choose = input.nextInt();
					switch(choose) {
						case 1://订餐页面
							System.out.println("\n");
							System.out.println("**********我要订餐*******");
							//判断订餐前提是订单未满，Boolean类型的变量表示订单是否已满。
							boolean isAdd = false;
							for(int i=0;i<names.length;i++) {
								if(names[i]==null) {
									isAdd =true;
									System.out.println("请输入订餐人姓名：");
									String name = input.next();
									System.out.println("序号\t菜名\t单价");
									for(int j=0;j<dishName.length;j++) {
										String praise = (praiseNum[j] ==0)?"":praiseNum[j]+"赞";
										System.out.println((j+1)+"\t"+dishName[j]+"\t"+prices[j]+"\t"+praise);
									}
									//菜品编号输入判断
									System.out.print("菜品编号：");
									int no = input.nextInt();
									while (no<1 ||no>dishName.length) {
										System.out.print("本店没有这个菜品，请重新选择：");
										no = input.nextInt();
									}
									System.out.print("请输入你要点的份数：");
									int number = input.nextInt();
									System.out.print("请输入送餐时间（10-20时之间）：");
									int time =input.nextInt();
									while (time<10 ||time>20) {
										System.out.print("抱歉超过配送时间，请重新选择：");
										time = input.nextInt();
									}
									System.out.println("请输入地址：");
									String address =input.next();
									//送餐信息
									String dishInfo = dishName[no-1]+" "+number+"份"; 
									System.out.println("订餐成功");
									System.out.println("你的订单是："+dishInfo);
									System.out.println("送餐时间是："+time+"点");
									//订单价格
									double dishPrice = (prices[no-1]*number);
									if(dishPrice>50) {
										System.out.println("订餐费用为：\t餐费："+dishPrice+"元\t配送费：0元\t总计："+dishPrice+"元");
									}else {
										System.out.println("订餐费用为：\t餐费："+dishPrice+"元\t配送费：6元\t总计："+(dishPrice+6)+"元");
									}
									
									//把订单信息添加到订单，插入订单的位置是关键：i
									names[i] = name;
									dishMsg[i] = dishInfo;
									times[i] = time;
									adresses[i] = address;
									sumPrices[i] =(dishPrice>50)?dishPrice:dishPrice+6;
									break;  //循环结束跳出循环
								}
							}
							if(!isAdd) {
								System.out.println("订单已满");
							}
							
							
							break;
						case 2:
							System.out.println("\n");
							System.out.println("**********查看餐袋*******");
							System.out.println("序号\t订餐人\t订餐菜品\t\t配送时间\t\t配送地址\t\t订单金额\t\t订单状态");
							for(int i=0;i<names.length;i++) {
								String time = times[i]+"点";
								String state = (states[i]==0)?"已预订":"已完成";//三目运算符：states[i]是否等于0是否为真，为真执行  ：之前表达式 。为假执行：之后表达式
								if(names[i]!=null) {     //如果姓名不为空输出
								System.out.println(+i+1+"\t"+names[i]+"\t"+dishMsg[i]+"\t\t"+time+"\t\t"+adresses[i]+"\t\t"+sumPrices[i]+"\t\t"+state);
								}
							}
							break;
						case 3:
							System.out.println("\n");
							System.out.println("**********签收订单*******");
							//签收订单前确定订单是否存在，flase-不存在，不能签收。TRUE -且预定状态能签收。存在-完成状态不能签收
							boolean isSign =false;
							System.out.println("请输入你要签收的订单编号：");
							int isSnNo = input.nextInt();
							for ( int i=0;i<names.length;i++){
								if(names[i]!=null && states[i]==0 && i==isSnNo-1) {//已预订状态
									isSign = true;
									states[i]=1;
									System.out.println("订单签收成功");
								}else if(names[i]!=null && states[i]==1 && i==isSnNo-1) {//已签收状态
									isSign = true;
									System.out.println("你选的订单已经签收，不能签收哦！");
								}
								
							}if(!isSign) {
								System.out.println("订单不存在");
							}
							break;
						case 4:
							System.out.println("\n");
							System.out.println("**********删除订单*******");
							boolean isDelete =false;
							System.out.println("请输入你要签收的订单编号：");
							int deleteNum = input.nextInt();
							for ( int i=0;i<names.length;i++){
								if(names[i]!=null && states[i]==0 && i==deleteNum-1) {//已预订状态
									isDelete = true;
									
									System.out.println("订单未签收，不能删除");
								}else if(names[i]!=null && states[i]==1 && i==deleteNum-1) {//已签收状态
									isDelete = true;
									//找到删除订单位置的下标 i;把i后的元素依次往前移动，做后一个元素置空
									for(int j=0;j<names.length-1;j++) {
										//前移
										names[j] = names[j+1];
										dishMsg[j] = dishMsg[j+1];
										times[j] = times[j+1];
										adresses[j] = adresses[j+1];
										sumPrices[j] = sumPrices[j+1];
										states[j] = states[j+1];
										
										
										
									}//置空
									names[names.length-1] =  null;
									dishMsg[dishMsg.length-1] = null;
									times[times.length-1] = 0;
									adresses[adresses.length-1] = null;
									sumPrices[sumPrices.length-1] = 0;
									states[states.length-1] = 0;
								}
								
								
							}if(!isDelete) {
								System.out.println("订单不存在");
							}
							break;
						case 5:
							System.out.println("\n");
							System.out.println("**********我要点赞*******");
							//循环输出菜品信息
							System.out.println("序号\t菜名\t单价");
							for(int j=0;j<dishName.length;j++) {
								String praise = (praiseNum[j] ==0)?"":praiseNum[j]+"赞";
								System.out.println((j+1)+"\t"+dishName[j]+"\t"+prices[j]+"\t"+praise);
							}
							 //点赞
							 System.out.println("请输入你要点赞的菜品序号");
							 int praiseno = input.nextInt();
							 while(praiseno<1 || praiseno>dishName.length) {
								 System.out.println("菜品输入错误，请重新输入：");
								 praiseno = input.nextInt();
							 }
							 praiseNum[praiseno-1]++;
							break;
						case 6:
							System.out.println("\n");
							System.out.println("**********退出系统*******");
							flag = true;//
							break;
						default:
							flag = true;
							break;
								
					}
					if(!flag) {      //!flag 等同于flag==false
						System.out.print("请输入0返回：");
						num = input.nextInt();
					}else {
						break;
					}
					
				}while(num==0);
				System.out.println("*****退出！");
						
			}
			
			}	
			
	


