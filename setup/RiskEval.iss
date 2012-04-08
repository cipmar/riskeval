[Setup]
AppName=RiskEval
AppVerName=RiskEval 1.0
DefaultDirName={pf}\RiskEval
DefaultGroupName=RiskEval
OutputBaseFilename=RiskEval Setup
Compression=lzma
SolidCompression=yes
SourceDir=setup files
OutputDir=../

[Languages]
Name: "english"; MessagesFile: "compiler:Default.isl"

[Tasks]
Name: "desktopicon"; Description: "{cm:CreateDesktopIcon}"; GroupDescription: "{cm:AdditionalIcons}"; Flags: unchecked
Name: "quicklaunchicon"; Description: "{cm:CreateQuickLaunchIcon}"; GroupDescription: "{cm:AdditionalIcons}"; Flags: unchecked

[Files]
Source: "RiskEval.exe"; DestDir: "{app}"; Flags: ignoreversion
Source: "clgcouple.hbm.xml"; DestDir: "{app}"; Flags: ignoreversion
Source: "company.hbm.xml"; DestDir: "{app}"; Flags: ignoreversion
Source: "consequence.hbm.xml"; DestDir: "{app}"; Flags: ignoreversion
Source: "consequencelocation.hbm.xml"; DestDir: "{app}"; Flags: ignoreversion
Source: "gpcouple.hbm.xml"; DestDir: "{app}"; Flags: ignoreversion
Source: "gravity.hbm.xml"; DestDir: "{app}"; Flags: ignoreversion
Source: "hibernate.cfg.xml"; DestDir: "{app}"; Flags: ignoreversion
Source: "probability.hbm.xml"; DestDir: "{app}"; Flags: ignoreversion
Source: "RiskEval.bat"; DestDir: "{app}"; Flags: ignoreversion
Source: "RiskEval.exe"; DestDir: "{app}"; Flags: ignoreversion
Source: "riskfactor.hbm.xml"; DestDir: "{app}"; Flags: ignoreversion
Source: "risklevel.hbm.xml"; DestDir: "{app}"; Flags: ignoreversion
Source: "risksheetconsequence.hbm.xml"; DestDir: "{app}"; Flags: ignoreversion
Source: "riskssheet.hbm.xml"; DestDir: "{app}"; Flags: ignoreversion
Source: "section.hbm.xml"; DestDir: "{app}"; Flags: ignoreversion
Source: "sector.hbm.xml"; DestDir: "{app}"; Flags: ignoreversion
Source: "workplace.hbm.xml"; DestDir: "{app}"; Flags: ignoreversion
Source: "database\*"; DestDir: "{app}\database"; Flags: confirmoverwrite recursesubdirs createallsubdirs; AfterInstall: AfterDatabaseCopy
Source: "icons\*"; DestDir: "{app}\icons"; Flags: ignoreversion recursesubdirs createallsubdirs
Source: "lib\*"; DestDir: "{app}\lib"; Flags: ignoreversion recursesubdirs createallsubdirs
Source: "ro\*"; DestDir: "{app}\ro"; Flags: ignoreversion recursesubdirs createallsubdirs
Source: "templates\*"; DestDir: "{app}\templates"; Flags: ignoreversion recursesubdirs createallsubdirs

[Icons]
Name: "{group}\RiskEval"; Filename: "{app}\RiskEval.exe"; WorkingDir: "{app}"
Name: "{group}\{cm:UninstallProgram,RiskEval}"; Filename: "{uninstallexe}"
Name: "{commondesktop}\RiskEval"; Filename: "{app}\RiskEval.exe"; Tasks: desktopicon; WorkingDir: "{app}"
Name: "{userappdata}\Microsoft\Internet Explorer\Quick Launch\RiskEval"; Filename: "{app}\RiskEval.exe"; Tasks: quicklaunchicon; WorkingDir: "{app}"

[Run]
Filename: "{app}\RiskEval.exe"; Description: "{cm:LaunchProgram,RiskEval}"; Flags: nowait postinstall skipifsilent

[Code]
procedure AfterDatabaseCopy;
var
  FileString: String;
  FileName: String;
  DatabaseDir: String;
begin
  FileName := AddBackslash(ExpandConstant('{app}')) + 'hibernate.cfg.xml';
  DatabaseDir := AddBackslash(ExpandConstant('{app}')) + 'database';
  LoadStringFromFile(FileName, FileString);
  StringChange(FileString, '$database.dir', DatabaseDir);
  SaveStringToFile(FileName, FileString, False);
end;
