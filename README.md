# [Traccar mod]
Traccar is an open source GPS tracking system version 4.10. <br/>
<b>This NOT ORGINAL repository!</b><br/>

## Overview
Серверная часть проекта Traccar, основанная на версии 4.10 с модификацией.

## MOD'S

 -  Add protocol NDTP v6.

## Build
1. Установить Java SE Development Kit версии страше 8. <br/>
2. Установить IDE (в моем случае VSCode). <br/>
3. В VSCode: установить расширение "Language support for Java ™ for Visual Studio Code". <br/>
4. В VSCode: установить расширение "Extension Pack for Java". <br/>
5. В VSCode: установить расширение "Maven for Java". <br/>
После внесения измений в проекте, использовать команду: <br/>
$ ./gradlew assemble
<br/>

## Install on ARM (Jetson Nano 2Gb, Raspbian Pi ...)
Необходим дистрибутив 64-bit ОС Debian или Ubuntu.<br/>
1. Все ARM машины имеют ограниченный объем ОЗУ, поэтому необходимы меры по экономии памяти. Избавляемся от "визуального режима":<br/>
"sudo systemctl set-default multi-user.target"<br/>
2. Создаем каталог "mkdir /opt/traccar" входим в каталог "cd /opt/traccar", копируем из каталога собранного проекта "<рабочий_каталог>/traccar/target/" содержимое:<br/>
каталог "lib" с содержимым, и файл "tracker-server.jar"<br/>
3. Создаем каталог "mkdir web" входим в каталог "cd web", копируем из каталога собранного проекта "<рабочий_каталог>/traccar/traccar-web/web" папку с содержимым.<br/>
4. Возвращаемся в каталог /opt/traccar. Создаем каталог "mkdir conf", копируем из каталога собранного проекта "<рабочий_каталог>/traccar/setup" файлы "default.xml" и "traccar.xml".<br/>
5. Возвращаемся в каталог /opt/traccar. Создаем каталог "mkdir schema", копируем из каталога собранного проекта "<рабочий_каталог>/traccar/schema" содержимое.<br/>
6. Возвращаемся в каталог /opt/traccar. Создаем каталог "mkdir templates", копируем из каталога собранного проекта "<рабочий_каталог>/traccar/templates" содержимое.<br/>
7. Возвращаемся в каталог /opt/traccar. Создаем каталог "mkdir logs", и каталог "mkdir data".<br/>
8. Переходим в каталог "cd /etc/systemd/system", создаем файл "traccar.service" редактируем "nano traccar.service", вводим в файле: <br/>
[Unit]<br/>
Description=traccar<br/>
After=network.target<br/>
Conflicts=shutdown.target<br/>
<br/>
[Service]<br/>
WorkingDirectory=/opt/traccar<br/>
SyslogIdentifier=traccar<br/>
ExecStart=/bin/sh -c "exec java -jar -Xms512M -Xmx1024M tracker-server.jar conf/traccar.xml"<br/>
Type=simple<br/>
Restart=on-failure<br/>
RestartSec=10<br/>
SuccessExitStatus=143<br/>
<br/>
[Install]<br/>
WantedBy=multi-user.target<br/>
<br/>
9. Сохраняем файл, и перезапускаем daemon командой "systemctl daemon-reload".<br/>
10. Активируем daemon командой "systemctl enable traccar.service".<br/>
11. Запускаем daemon командой "systemctl start traccar.service".<br/>
12. Смотрим состояние daemon командой "systemctl status traccar.service", ошибок не должно быть, иначе исправлем их.<br/>
По умолчанию traccar сервер использует порт 8082. В веб-браузере вводим:  http://<ip_адрес_сервера>:8082<br/> 

## License

    Apache License, Version 2.0

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
