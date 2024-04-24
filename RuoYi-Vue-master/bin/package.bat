@echo off
echo.
echo [信息] 打包Web工程，生成war/jar包文件。
echo.

net stop frpc
%~d0
cd %~dp0

cd ..
call mvn clean package -Dmaven.test.skip=true

pause