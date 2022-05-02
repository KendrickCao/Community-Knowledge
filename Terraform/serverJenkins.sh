#!/bin/bash
cd /home/debian
echo in directory $PWD

sudo apt-get update
sudo apt-get install unzip -y
sudo apt-get install git -y
sudo apt-get install curl -y

# echo "installing gitlab server key..."
# sudo touch ~/.ssh/known_hosts
# sudo ssh-keyscan git.cardiff.ac.uk >> ~/.ssh/known_hosts 
# sudo chmod 644 ~/.ssh/known_hosts
# cat<< `EOF` >> Team4_project_keypair.key
# -----BEGIN OPENSSH PRIVATE KEY-----
# b3BlbnNzaC1rZXktdjEAAAAABG5vbmUAAAAEbm9uZQAAAAAAAAABAAABlwAAAAdzc2gtcn
# NhAAAAAwEAAQAAAYEA37MHegTEFYzdsQX0MRfMJ0yRi1zXsoi9rYOXu2R5el4PvTTGTdb9
# Pkc2wKZzNU9+RBirMMWbBqMya66oAuprtn61ULtKsy4cESTr1khuyLzt7KXNujIHNs47uA
# HALxofiE1SwW+DWUUvnjxskmdz6+W/eiflHqRjhrfYTpkr6NNyk8I0tEvymMmPBsG/eYhE
# tKs43mH8Fwmk/o1uxZ72ahz0P8R1CE6WcZcwx31rSnEG8rWRVR0jhv8UVVOVQ5XLT48dZN
# mTh4R7a99M3CCCu5sYLkRDfGGNJwqGFTDyUPxBoT8oqfuph9Gt2deLCEVCGCyhlJVdzRHg
# 9qbn4BnvTbEiezBQ8dfSXsIojYci6CBEBLqUfdoYS3/kPR+Dz1Y7LMtk5gwrpsxI9GiDY0
# z5SePYCovyjKhXa4SbnaJuezKQPd8cdtgGSjyGLFW5WqyeDTpCtU8K+YN3wvfMJVKKLWdw
# SFapJ5/dcWzYD1Sz5XMEY/6gpM0UUrhzPGy2jxydAAAFmDayNWM2sjVjAAAAB3NzaC1yc2
# EAAAGBAN+zB3oExBWM3bEF9DEXzCdMkYtc17KIva2Dl7tkeXpeD700xk3W/T5HNsCmczVP
# fkQYqzDFmwajMmuuqALqa7Z+tVC7SrMuHBEk69ZIbsi87eylzboyBzbOO7gBwC8aH4hNUs
# Fvg1lFL548bJJnc+vlv3on5R6kY4a32E6ZK+jTcpPCNLRL8pjJjwbBv3mIRLSrON5h/BcJ
# pP6NbsWe9moc9D/EdQhOlnGXMMd9a0pxBvK1kVUdI4b/FFVTlUOVy0+PHWTZk4eEe2vfTN
# wggrubGC5EQ3xhjScKhhUw8lD8QaE/KKn7qYfRrdnXiwhFQhgsoZSVXc0R4Pam5+AZ702x
# InswUPHX0l7CKI2HIuggRAS6lH3aGEt/5D0fg89WOyzLZOYMK6bMSPRog2NM+Unj2AqL8o
# yoV2uEm52ibnsykD3fHHbYBko8hixVuVqsng06QrVPCvmDd8L3zCVSii1ncEhWqSef3XFs
# 2A9Us+VzBGP+oKTNFFK4czxsto8cnQAAAAMBAAEAAAGAbYiiDpMq2EqDetypdyhp5IxPb+
# fkDh16ku2ubCmEefB40HJ470Bn/AiuvKJEI0RrVTinCliX0iYy2BtLVTInz9I3QfKTXy0i
# vRpRCDmrVUt9TLTbCWLZuXI+N5lT2zx1WC5UaTcLH7kK2fZIS1+wmT+EBHJodBqWSS7YEZ
# RatcRjhApB+E5/2WRITkysTvv0eGqmi+16nGUyjxn9tXJiwg6vYlH15D1m2PPpBKVa1baG
# am2RXhXIgZ+A+07T9aXwXuE+VpURae9M97ycIIyvcqL+LXo9x4G8IuIZmASEWPKF9lxCL9
# ly5v8qBetmNJfD3do/McnhTOK0NnXH3flUD8bQSxg7sAiUV1KSmkr8lBfvzhE4YIZ3wJBw
# 9LtFIpE0gm/IctBYVi6f4rBQrEzxXjEr8H8+x1V0PSF41cF2MI9TFzoGIBODcAQ4TJ2/8r
# zRdO6S0XFDSI4+hC6FPontxwMznAttb7CqMoqXiUZpKFOsOOEuUojJ0eM8q7CozfDZAAAA
# wAPbt6MXNNEOm+KeFuJFhzr/xVUb9N7srOvFMhxdeIv6qiJFmG+7jCwn3CmvznL3m2nvQv
# JpDbftJB26mHV72nXSXDZdzjezfE53fydbzhY8lXDU0pyQsAAkrpCZyRYe81AzgVp01P0z
# etlXKqrH++MiGMdylubn/zg2oPQXEa01o7UzFyDI/x2g45jOt8oZEDiZdRax0TEFkLEdtm
# viMUpg3Fobd6LeqjKTnuXRMsHxoSd66jPaySef3lW2umG61QAAAMEA+AzuOAr+87k6s2H3
# oZbHvx2X6sw+KxVZoMt9wILX1JRae9StsyxWu7HoOMnqOwWi+5MxOOxpFJZlYerUpO198P
# GnXp0+H5t5yvJeD1eQG0taTSWt0kEiQREHJzcj9CevdS3xB5ZEElMBqCa3kDsbMtaCuUTA
# zCM5GQNrLn6vAuqcd6GRhIXsbFqTho6AlKuC6bzfAJ1FCIUEjEGr+BIAn3CY3Ot53/2qcl
# 5DrAyMiAQbPWCOV03FdVMVVx5naPl3AAAAwQDm3lC++gKgtj+oywkU4J8+6G0AgHeK1fHZ
# IZdubkew7FbTMomL1WmLuotmdGTj68YPrMCQblP/oFB6FvPx8iymlND7m2VWye3GSVJgNZ
# 0M95mnfZ51nVShZh/AsIhV7AEaGoy46W0vGmimSkoWpI/F8qe+i5mJFwwlDQEV4mFVnUyL
# V1TXiPFUGMyOVvkTosca37DliEzPv0qGVc8l729VMCW3qPN8rv8BQKFYLXECCSDs8cdIs6
# +YadyWhlsH34sAAAAcSUQrYzIxMTA2Nzg0QE5TQUY0N0IwOTJBODBCRQECAwQFBgc=
# -----END OPENSSH PRIVATE KEY-----
# `EOF`
# chmod 400 Team4_project_keypair.key
# ssh-agent bash -c 'ssh-add Team4_project_keypair.key; git clone git@git.cardiff.ac.uk:c21106784/fork-community-knowledge-website.git'


echo "Installing Java 11..."
sudo apt-get install default-jdk -y
echo java -version

# echo "Installing Jenkins"
# # https://pkg.jenkins.io/debian-stable/
# curl -fsSL https://pkg.jenkins.io/debian-stable/jenkins.io.key | sudo tee /usr/share/keyrings/jenkins-keyring.asc > /dev/null
# echo deb [signed-by=/usr/share/keyrings/jenkins-keyring.asc] https://pkg.jenkins.io/debian-stable binary/ | sudo tee /etc/apt/sources.list.d/jenkins.list > /dev/null
# sudo apt-get update
# sudo apt-get install jenkins -y
# # If you want jenkins on port 8081 so you can run your app on 8080 then change the default jenkins port.
# #(look up linux sed - it is really cool)
# # sudo sed -i 's/JENKINS_PORT="8080"/JENKINS_PORT="8081"/g' /etc/sysconfig/jenkins
# sudo systemctl start jenkins
# sudo systemctl status jenkins
# sudo systemctl enable jenkins


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

# echo "Installing maven..."
# wget https://dlcdn.apache.org/maven/maven-3/3.8.5/binaries/apache-maven-3.8.5-bin.tar.gz -P /tmp
# sudo tar xf /tmp/apache-maven-3.8.5-bin.tar.gz -C /opt
# sudo ln -s /opt/apache-maven-3.8.5 /opt/maven

echo "Installing jfrog cli..."
sudo curl -fL https://install-cli.jfrog.io | sh

# export M2_HOME=/opt/maven
# export MAVEN_HOME=/opt/maven
# export PATH=${M2_HOME}/bin:${PATH}
# echo "mvn -version"
# mvn -version
echo "Installing nginx..."
sudo apt update
sudo apt install nginx -y
cd /etc/nginx/sites-available
cat<< `EOF` >> pipeline
server {
        listen       80;
        server_name  10.72.101.75;
        location / {
        proxy_pass http://myproject;
    }
}
`EOF`

cd /etc/nginx
sudo rm -f nginx.conf
cat<< `EOF` >> nginx.conf
user www-data;
worker_processes auto;
pid /run/nginx.pid;
include /etc/nginx/modules-enabled/*.conf;

events {
        worker_connections 768;
        # multi_accept on;
}

http {
    upstream myproject{
        ip_hash;
        server 10.72.101.75:8081;
        server 10.72.101.75:8080;
    }

        ##
        # Basic Settings
        ##

        sendfile on;
        tcp_nopush on;
        tcp_nodelay on;
        keepalive_timeout 65;
        types_hash_max_size 2048;
        # server_tokens off;

        # server_names_hash_bucket_size 64;
        # server_name_in_redirect off;

        include /etc/nginx/mime.types;
        default_type application/octet-stream;

        ##
        # SSL Settings
        ##

        ssl_protocols TLSv1 TLSv1.1 TLSv1.2; # Dropping SSLv3, ref: POODLE
        ssl_prefer_server_ciphers on;

        ##
        # Logging Settings
        ##

        access_log /var/log/nginx/access.log;
        error_log /var/log/nginx/error.log;

        ##
        # Gzip Settings
        ##

        gzip on;

        # gzip_vary on;
        # gzip_proxied any;
        # gzip_comp_level 6;
        # gzip_buffers 16 8k;
        # gzip_http_version 1.1;
        # gzip_types text/plain text/css application/json application/javascript text/xml application/xml application/xml+rss text/javascript;

        ##
        # Virtual Host Configs
        ##

        include /etc/nginx/conf.d/*.conf;
        include /etc/nginx/sites-enabled/*;
}


#mail {
#       # See sample authentication script at:
#       # http://wiki.nginx.org/ImapAuthenticateWithApachePhpScript
#
#       # auth_http localhost/auth.php;
#       # pop3_capabilities "TOP" "USER";
#       # imap_capabilities "IMAP4rev1" "UIDPLUS";
#
#       server {
#               listen     localhost:110;
#               protocol   pop3;
#               proxy      on;
#       }
#
#       server {
#               listen     localhost:143;
#               protocol   imap;
#               proxy      on;
#       }
#}
`EOF`
sudo ln -s /etc/nginx/sites-available/pipeline /etc/nginx/sites-enabled/
sudo nginx -t
sudo systemctl restart nginx 

echo n|jf rt dl "maven-challenge-local/com/community/client/0.0.1-SNAPSHOT/*.jar" --sort-by=created --sort-order=desc --limit=1 --url=https://c21106784.jfrog.io/artifactory/ --user=CaoY35@cardiff.ac.uk --password=Loveyou.1997
cd com/community/client/0.0.1-SNAPSHOT
nohup java -jar *.jar --server.port=8081 &
nohup java -jar *.jar --server.port=8080 &

# cd fork-community-knowledge-website/
# mvn clean package
# java -jar target/client-0.0.1-SNAPSHOT.jar --server.port=8081
