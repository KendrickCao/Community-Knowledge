variable "flavor" { default = "m1.large" }
variable "image" { default = "Debian Buster 10.11.1 20211029" }
#variable "instance" { default = "tf_instance" }

variable "name" { default = "c21106784_Debian_test" }

variable "network" { default = "c21106784_network" }   # you need to change this

variable "keypair" { default = "c21106784_keypair" } # you need to change this
variable "pool" { default = "cscloud_private_floating" }
variable "server_script" { default = "./serverJenkins.sh" }
variable "security_description" { default = "Terraform security group for Debian" }
variable "security_name" { default = "tf_security_debian" }
