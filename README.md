# 自定义 springboot 项目脚手架

## Step 1

* 进入steven-maven-archetype根目录
* 执行 mvn archetype:create-from-project

## Step 2

* 修改配置文件
* 在第Step 1 执行后,在项目 target/generated-sources/archetype下进行文件替换—————此步操作是为了避免生成的项目包名等未改变，系统报错

修改内容一般为,eg:

~~~
.idea 文件删除
.mvn 文件删除
.iml 文件删除

`com.steven.maven.archetype` 替换为 ${package}
`0.0.1-SNAPSHOT` 替换为${versionn}

id="adapter" 替换为 id="__artifactId__-adapter"
id="infra" 替换为 id="__artifactId__-infra"
id="application" 替换为 id="__artifactId__-application"
id="domain" 替换为 id="__artifactId__-domain"
id="start" 替换为 id="__artifactId__-start"

dir="adapter" 替换为 dir="__artifactId__-adapter"
dir="infra" 替换为 dir="__artifactId__-infra"
dir="application" 替换为 dir="__artifactId__-application"
dir="domain" 替换为 dir="__artifactId__-domain"
dir="start" 替换为 dir="__artifactId__-start"

name="adapter" 替换为 name="__artifactId__-adapter"
name="infra" 替换为 name="__artifactId__-infra"
name="application" 替换为 name="__artifactId__-application"
name="domain" 替换为 name="__artifactId__-domain"
name="start" 替换为 name="__artifactId__-start"

~~~

