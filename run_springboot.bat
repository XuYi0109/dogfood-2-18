@echo off
chcp 65001
setlocal enabledelayedexpansion

echo.
echo ========================================
echo Starting Spring Boot Application
echo ========================================
echo.

echo Checking Java version...
java -version
if errorlevel 1 (
    echo ERROR: Java not found!
    pause
    exit /b 1
)

echo.
echo Cleaning and compiling project...
call mvnw.cmd clean compile -q
if errorlevel 1 (
    echo ERROR: Compilation failed!
    pause
    exit /b 1
)
echo Compilation successful!

echo.
echo Starting Spring Boot...
echo Application will be available at: http://localhost:8080
echo.
call mvnw.cmd spring-boot:run

pause
