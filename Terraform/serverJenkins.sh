#!/bin/bash
cd /home/debian
echo in directory $PWD

sudo apt-get update
sudo apt-get install unzip -y
sudo apt-get install git -y
sudo apt-get install curl -y

echo "installing gitlab server key..."
sudo touch ~/.ssh/known_hosts
sudo ssh-keyscan git.cardiff.ac.uk >> ~/.ssh/known_hosts 
sudo chmod 644 ~/.ssh/known_hosts

echo "Installing Java 11..."
sudo apt-get install default-jdk -y
echo java -version

echo "Installing Jenkins"
# https://pkg.jenkins.io/debian-stable/
curl -fsSL https://pkg.jenkins.io/debian-stable/jenkins.io.key | sudo tee /usr/share/keyrings/jenkins-keyring.asc > /dev/null
echo deb [signed-by=/usr/share/keyrings/jenkins-keyring.asc] https://pkg.jenkins.io/debian-stable binary/ | sudo tee /etc/apt/sources.list.d/jenkins.list > /dev/null
sudo apt-get update
sudo apt-get install jenkins -y
# If you want jenkins on port 8081 so you can run your app on 8080 then change the default jenkins port.
#(look up linux sed - it is really cool)
# sudo sed -i 's/JENKINS_PORT="8080"/JENKINS_PORT="8081"/g' /etc/sysconfig/jenkins
sudo systemctl start jenkins
sudo systemctl status jenkins
sudo systemctl enable jenkins


echo "installing MariaDB..."
# sudo yum install mysql -y
sudo apt-get install mariadb-server -y
sudo systemctl start mariadb
sudo systemctl status mariadb
sudo systemctl enable mariadb

sudo mysql -u root -e "USE mysql; CREATE database client;"
sudo mysql -u root -e -pcomsc "USE mysql; UPDATE user SET password=PASSWORD('comsc') WHERE User='root' AND Host = 'localhost'; FLUSH PRIVILEGES;"
sudo mysql -u root -e "ALTER USER 'root'@'localhost' IDENTIFIED VIA mysql_native_password;"
sudo mysql -u root -e "ALTER USER 'root'@'localhost' IDENTIFIED BY 'comsc';"



# echo "Installing gradle..."
# wget https://services.gradle.org/distributions/gradle-6.7.1-bin.zip
# sudo mkdir /opt/gradle
# sudo unzip -d /opt/gradle gradle-6.7.1-bin.zip
# export PATH=$PATH:/opt/gradle/gradle-6.7.1/bin
# echo gradle -v

echo "Installing terraform..."
cd /home/debian
wget https://releases.hashicorp.com/terraform/1.1.5/terraform_1.1.5_linux_amd64.zip
unzip terraform_1.1.5_linux_amd64.zip
sudo mv terraform /usr/local/bin/

echo "Installing maven..."
wget https://dlcdn.apache.org/maven/maven-3/3.8.5/binaries/apache-maven-3.8.5-bin.tar.gz -P /tmp
sudo tar xf /tmp/apache-maven-3.8.5-bin.tar.gz -C /opt
sudo ln -s /opt/apache-maven-3.8.5 /opt/maven

export M2_HOME=/opt/maven
export MAVEN_HOME=/opt/maven
export PATH=${M2_HOME}/bin:${PATH}
echo mvn -version
mvn -version
# mvn compile	
mvn clean package
java -jar target/client-0.0.1-SNAPSHOT.jar --server.port=8081
# cd /var/lib/jenkins/workspace/c21106784_debian_project
# java -jar target/client-0.0.1-SNAPSHOT.jar --server.port=8081
