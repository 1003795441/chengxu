package com.vacn.chengxu.mavenProje;

import java.lang.reflect.Field;
import java.util.Map;

public class Books {
   //书名
   private String bookName;
   private String num;
   public String type;
   private Integer size;
   
	public Integer getSize() {
	return size;
}
public void setSize(Integer size) {
	this.size = size;
}
	public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
    }
	
	public static void getFileds(Class<?> classObject,Object object) throws IllegalArgumentException, IllegalAccessException{
		Field [] fieldList=classObject.getDeclaredFields();
		for(Field field:fieldList){
			System.out.println(field.getName()+"的值:"+field.get(object));
		}
	}
	
	public static void copySameProperities(Object targect,Object sources) throws IllegalArgumentException, IllegalAccessException{
		Field [] targetList=targect.getClass().getDeclaredFields();
		Field [] sourcesList=sources.getClass().getDeclaredFields();
		for(Field field:targetList){
			for(Field field1:sourcesList){
				if(field.getName().equals(field1.getName())){
					field.set(field.get(targect),field1.get(sources));
					System.out.println("aa"+field.get(targect));
					break;
				}
			}
		}
	}
	public static boolean copyProperties(Object targect,Object sources){
		try{
			Field [] fieldList=sources.getClass().getDeclaredFields();
			for(Field filed:fieldList){
				System.out.println(filed.getType().getName());
				if(filed.getType().getName().equals("java.lang.Integer")){
					continue;
				}
				filed.set(targect, filed.get(sources));
			}
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
   public static void main(String[] args) throws Exception {
	   Books book=new Books();
	   book.setBookName("activiti实战");
	   book.setNum("hehe");
	   book.setSize(213);
	   Books1 book1=new Books1();
	   copySameProperities(book1,book);
	   System.out.println(book1.getBookName());
	   System.out.println(book1.getSize());
	   
}
}
