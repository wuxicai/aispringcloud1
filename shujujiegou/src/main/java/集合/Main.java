package 集合;

import 集合.Times.Task;
import 集合.file.FileInfo;
import 集合.file.Files;
import 集合.set.ListSet;
import 集合.set.Set;
import 集合.set.Set.Visitor;
import 集合.set.TreeSet;

public class Main {
	
	static void test1() {

		Set<Integer> listSet = new ListSet<>();
		listSet.add(10);
		listSet.add(11);
		listSet.add(11);
		listSet.add(12);
		listSet.add(10);
		
		Set<Integer> treeSet = new TreeSet<>();
		treeSet.add(12);
		treeSet.add(10);
		treeSet.add(7);
		treeSet.add(11);
		treeSet.add(10);
		treeSet.add(11);
		treeSet.add(9);
		
		treeSet.traversal(new Visitor<Integer>() {
			@Override
			public boolean visit(Integer element) {
				System.out.println(element);
				return false;
			}
		});
	}
	
	
	static void testSet(Set<String> set, String[] words) {
		for (int i = 0; i < words.length; i++) {
			set.add(words[i]);
		}
		for (int i = 0; i < words.length; i++) {
			set.contains(words[i]);
		}
		for (int i = 0; i < words.length; i++) {
			set.remove(words[i]);
		}
	}
	
	static void test2() {
		FileInfo fileInfo = Files.read("D:\\JAVA\\src\\java\\util",
				new String[]{"java"});
		
		System.out.println("文件数量：" + fileInfo.getFiles());
		System.out.println("代码行数：" + fileInfo.getLines());
		String[] words = fileInfo.words();
		System.out.println("单词数量：" + words.length);
		
		Times.test("ListSet", new Task() {
			public void execute() {
				testSet(new ListSet<>(), words);
			}
		});
		
		Times.test("TreeSet", new Task() {
			public void execute() {
				testSet(new TreeSet<>(), words);
			}
		});
	}

	public static void main(String[] args) {

		test2();
	}

}
