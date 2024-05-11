@echo off

net start frpc
cd ..\ruoyi-admin\target
python -m http.server 8888

pause
