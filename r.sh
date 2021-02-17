#########################################
#modify to according to your env
#
#If you installed JDK, but can not find jre path, try:
#java -XshowSettings:properties
#
#On Linux, maybe
#JAVA_HOME="/usr/lib/jvm/java-1.8.0-openjdk-amd64/jre"
#########################################
JAVA_HOME=/home/allen/jdk/jdk1.8/jre
########################################
MY_SAMPLE=com.github.allenduke.avm.Test
##############################################
function join_by { local IFS="$1"; shift; echo "$*"; }
##############################################

#FIXME: win should be ';'
SEP=':'

jars=$JAVA_HOME/lib/*.jar
JDK=$(join_by $SEP ${jars[@]})

export JAVA_HOME
#java com.github.allenduke.avm.Main -cp $JDK $MY_SAMPLE
java com.github.allenduke.avm.Main -Xjre $JAVA_HOME $MY_SAMPLE