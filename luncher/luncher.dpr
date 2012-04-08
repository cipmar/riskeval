program luncher;

uses
  Forms, ShellApi, Windows;

{$R *.res}

var
  classPath: String;
  classPathParam : String;
begin
  Application.Initialize;
  Application.Run;
  
  classPath :=
    '.;'+
    'lib/commons-logging.jar;'+
    'lib/antlr-2.7.5H3.jar;'+
    'lib/hibernate3.jar;'+
    'lib/asm.jar;'+
    'lib/cglib-2.1.jar;'+
    'lib/commons-collections-3.1.jar;'+
    'lib/connector.jar;'+
    'lib/dom4j-1.6.jar;'+
    'lib/ehcache-1.1.jar;'+
    'lib/firebirdsql.jar;'+
    'lib/jta.jar;'+
    'lib/c3p0-0.9.0.jar;'+
    'lib/rtftemplate-1.0.1-b8.jar;'+
    'lib/spring.jar;'+
    'lib/freemarker.jar;'+
    'lib/velocity-1.4.jar;'+
    'lib/dom4j-1.6.jar;'+
    'lib/commons-digester-1.7.jar;'+
    'lib/commons-beanutils.jar;'+
    'lib/logkit-1.0.1.jar';

  classPathParam := '-cp ' + '"' + classPath + '" ' + 'ro.rmc.riskeval.gui.Splasher';
  ShellExecute(0, 'OPEN', 'javaw.exe', PAnsiChar(classPathParam), '.', SW_NORMAL);
end.
