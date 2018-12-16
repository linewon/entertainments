最根本的pom.xml
只定义所有关联项目共享的东西。例如：版本号，字符编码，编译器版本

此后项目分为service(e.g. springboot applications)和tool(.jar)

所有service继承一个二级pom，pom里定义springboot相关内容

tool则看情况决定需不需要新的pom，也可以直接继承根pom(只用得到版本号相关的东西)



some important labels in pom:
	dependencyManagement
	pluginManagement
	parent
	modules
	plugin