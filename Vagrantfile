# -*- mode: ruby -*-
# vi: set ft=ruby :

MACHINE_CPU_COUNT = "4"
MACHINE_RAM_MB = "8192"

Vagrant.configure("2") do |config|
  config.vm.define "vm-client1" do |vmc|
    vmc.vm.box = "bento/ubuntu-20.04"
    vmc.vm.hostname = "vm-client.example.com"
    vmc.vm.network "private_network", ip: "192.168.56.100"

    vmc.vm.provider "virtualbox" do |v|
      v.name = "vm-client1"
      v.customize ["modifyvm", :id, "--groups", "/Programowanie"]
      v.customize ["modifyvm", :id, "--cpus", MACHINE_CPU_COUNT]
      v.customize ["modifyvm", :id, "--memory", MACHINE_RAM_MB]
    end

    vmc.vm.provision "shell", path: "client-init.sh"
  end

  config.vm.define "vm-client2" do |vmc|
    vmc.vm.box = "bento/ubuntu-20.04"
    vmc.vm.hostname = "vm-client2.example.com"
    vmc.vm.network "private_network", ip: "192.168.56.101"

    vmc.vm.provider "virtualbox" do |v|
      v.name = "vm-client2"
      v.customize ["modifyvm", :id, "--groups", "/Programowanie"]
      v.customize ["modifyvm", :id, "--cpus", MACHINE_CPU_COUNT]
      v.customize ["modifyvm", :id, "--memory", MACHINE_RAM_MB]
    end

    vmc.vm.provision "shell", path: "client-init.sh"
  end

  config.vm.define "vm-server" do |vmc|
    vmc.vm.box = "bento/ubuntu-20.04"
    vmc.vm.hostname = "vm-server.example.com"
    vmc.vm.network "private_network", ip: "192.168.56.105"

    vmc.vm.provider "virtualbox" do |v|
      v.name = "vm-server"
      v.customize ["modifyvm", :id, "--groups", "/Programowanie"]
      v.customize ["modifyvm", :id, "--cpus", "2"]
      v.customize ["modifyvm", :id, "--memory", "4096"]
    end
    
    vmc.vm.provision "shell", path: "server-init.sh"
  end

end
