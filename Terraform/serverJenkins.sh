#!/bin/bash
cd /home/debian
echo in directory $PWD

sudo apt-get update
sudo apt-get install unzip -y
sudo apt-get install git -y
sudo apt-get install curl -y

echo "Installing Java 11..."
sudo apt-get install default-jdk -y
echo java -version

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

echo "Installing terraform..."
cd /home/debian
wget https://releases.hashicorp.com/terraform/1.1.5/terraform_1.1.5_linux_amd64.zip
unzip terraform_1.1.5_linux_amd64.zip
sudo mv terraform /usr/local/bin/


echo "Installing jfrog cli..."
sudo curl -fL https://install-cli.jfrog.io | sh


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
