@echo off
echo Starting Spring Boot Application...
echo.

where java >nul 2>&1
if %errorlevel% equ 0 (
    echo Java found in PATH
    java -version
) else (
    echo Java NOT found in PATH!
    exit /b 1
)

echo.
echo Compiling project...
call mvnw.cmd clean compile > compile.log 2>&1
if %errorlevel% equ 0 (
    echo Compilation SUCCESSFUL
) else (
    echo Compilation FAILED - check compile.log
    type compile.log
    exit /b 1
)

echo.
echo Starting application...
call mvnw.cmd spring-boot:run
