/**
 * zookeeper 启动命令 必须加-p 否则宿主机器访问不到
 * docker run --name ronda-zookeeper --restart always -p 2181:2181 -d zookeeper:3.4.13
 * 创建node的时候需要一级一级创建
 */
package cn.kevin.zookeeper;