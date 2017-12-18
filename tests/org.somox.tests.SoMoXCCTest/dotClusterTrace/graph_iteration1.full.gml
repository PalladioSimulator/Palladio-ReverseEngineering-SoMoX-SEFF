graph [
directed 1
node [
name "org.somox.core.WithCore
"
label "org.somox.core.WithCore
"
graphics
[hasFill 0
]
]
node [
name "org.somox.core.Core
org.somox.core.ICoreParent
org.somox.core.IProvided
org.somox.core.ICore
"
label "org.somox.core.Core
org.somox.core.ICoreParent
org.somox.core.IProvided
org.somox.core.ICore
"
graphics
[hasFill 0
]
]
node [
name "org.somox.core.Helper
"
label "org.somox.core.Helper
"
graphics
[hasFill 0
]
]
node [
name "org.somox.core.subcore.MyClass2
"
label "org.somox.core.subcore.MyClass2
"
graphics
[hasFill 0
]
]
node [
name "org.somox.core.subcore.MyClass1
"
label "org.somox.core.subcore.MyClass1
"
graphics
[hasFill 0
]
]
node [
name "org.somox.core.subcore.sub2.SubSubClass
"
label "org.somox.core.subcore.sub2.SubSubClass
"
graphics
[hasFill 0
]
]
node [
name "org.somox.core.subcore2.MySingleClass
"
label "org.somox.core.subcore2.MySingleClass
"
graphics
[hasFill 0
]
]
edge [
source "org.somox.core.subcore.MyClass1
"
target "org.somox.core.subcore.MyClass2
"
label "AfferentCoupling = 7,00
DefaultMergeIndicatingMetric = ,46
SubsystemComponent = ,00
Abstractness = ,00
PackageMapping = 1,00
ExternalAccessesCount = 4,00
AbstractTypesCount = ,00
Instability = ,36
DMS = ,64
AdherenceToInterfaceCommunication = ,00
Coupling = ,50
SliceLayerArchitectureQuality = 1,00
TotalTypesCount = 2,00
EfferentCoupling = 4,00
InternalAccessesCount = 2,00
NameResemblance = ,97
DirectoryMapping = 1,00
DefaultCompositionIndicatingMetric = ,42
InterfaceAccessesCount = ,00
"
graphics
[
targetArrow "standard"
]
]
edge [
source "org.somox.core.subcore.MyClass2
"
target "org.somox.core.subcore.MyClass1
"
label "AfferentCoupling = 7,00
DefaultMergeIndicatingMetric = ,46
SubsystemComponent = ,00
Abstractness = ,00
PackageMapping = 1,00
ExternalAccessesCount = 4,00
AbstractTypesCount = ,00
Instability = ,36
AdherenceToInterfaceCommunication = ,00
DMS = ,64
Coupling = ,50
SliceLayerArchitectureQuality = 1,00
TotalTypesCount = 2,00
EfferentCoupling = 4,00
InternalAccessesCount = 2,00
NameResemblance = ,97
DirectoryMapping = 1,00
DefaultCompositionIndicatingMetric = ,42
InterfaceAccessesCount = ,00
"
graphics
[
targetArrow "standard"
]
]
edge [
source "org.somox.core.subcore.sub2.SubSubClass
"
target "org.somox.core.Core
org.somox.core.ICoreParent
org.somox.core.IProvided
org.somox.core.ICore
"
label "AfferentCoupling = 3,00
DefaultMergeIndicatingMetric = ,24
SubsystemComponent = ,00
Abstractness = ,60
PackageMapping = ,60
ExternalAccessesCount = 5,00
AbstractTypesCount = 3,00
Instability = ,87
DMS = ,47
AdherenceToInterfaceCommunication = ,00
Coupling = ,00
SliceLayerArchitectureQuality = ,33
TotalTypesCount = 5,00
EfferentCoupling = 20,00
InternalAccessesCount = ,00
NameResemblance = ,11
DirectoryMapping = ,78
DefaultCompositionIndicatingMetric = ,21
InterfaceAccessesCount = ,00
"
graphics
[
targetArrow "standard"
]
]
edge [
source "org.somox.core.Core
org.somox.core.ICoreParent
org.somox.core.IProvided
org.somox.core.ICore
"
target "org.somox.core.subcore.sub2.SubSubClass
"
label "AfferentCoupling = 3,00
DefaultMergeIndicatingMetric = ,24
SubsystemComponent = ,00
Abstractness = ,60
PackageMapping = ,60
ExternalAccessesCount = 15,00
AbstractTypesCount = 3,00
Instability = ,87
AdherenceToInterfaceCommunication = ,00
DMS = ,47
Coupling = ,00
SliceLayerArchitectureQuality = ,33
TotalTypesCount = 5,00
EfferentCoupling = 20,00
InternalAccessesCount = ,00
NameResemblance = ,11
DirectoryMapping = ,78
DefaultCompositionIndicatingMetric = ,21
InterfaceAccessesCount = ,00
"
graphics
[
targetArrow "standard"
]
]
edge [
source "org.somox.core.subcore.MyClass2
"
target "org.somox.core.WithCore
"
label "AfferentCoupling = 13,00
DefaultMergeIndicatingMetric = ,32
SubsystemComponent = ,33
Abstractness = ,00
PackageMapping = ,75
ExternalAccessesCount = 4,00
AbstractTypesCount = ,00
Instability = ,35
DMS = ,65
AdherenceToInterfaceCommunication = ,00
Coupling = ,00
SliceLayerArchitectureQuality = ,33
TotalTypesCount = 2,00
EfferentCoupling = 7,00
InternalAccessesCount = ,00
NameResemblance = ,42
DirectoryMapping = ,88
DefaultCompositionIndicatingMetric = ,28
InterfaceAccessesCount = ,00
"
graphics
[
targetArrow "standard"
]
]
edge [
source "org.somox.core.WithCore
"
target "org.somox.core.subcore.MyClass2
"
label "AfferentCoupling = 13,00
DefaultMergeIndicatingMetric = ,32
SubsystemComponent = ,33
Abstractness = ,00
PackageMapping = ,75
ExternalAccessesCount = 3,00
AbstractTypesCount = ,00
Instability = ,35
AdherenceToInterfaceCommunication = ,00
DMS = ,65
Coupling = ,00
SliceLayerArchitectureQuality = ,33
TotalTypesCount = 2,00
EfferentCoupling = 7,00
InternalAccessesCount = ,00
NameResemblance = ,42
DirectoryMapping = ,88
DefaultCompositionIndicatingMetric = ,28
InterfaceAccessesCount = ,00
"
graphics
[
targetArrow "standard"
]
]
edge [
source "org.somox.core.subcore.MyClass1
"
target "org.somox.core.WithCore
"
label "AfferentCoupling = 16,00
DefaultMergeIndicatingMetric = ,32
SubsystemComponent = ,33
Abstractness = ,00
PackageMapping = ,75
ExternalAccessesCount = 4,00
AbstractTypesCount = ,00
Instability = ,30
DMS = ,70
AdherenceToInterfaceCommunication = ,00
Coupling = ,00
SliceLayerArchitectureQuality = ,33
TotalTypesCount = 2,00
EfferentCoupling = 7,00
InternalAccessesCount = ,00
NameResemblance = ,42
DirectoryMapping = ,88
DefaultCompositionIndicatingMetric = ,28
InterfaceAccessesCount = ,00
"
graphics
[
targetArrow "standard"
]
]
edge [
source "org.somox.core.WithCore
"
target "org.somox.core.subcore.MyClass1
"
label "AfferentCoupling = 16,00
DefaultMergeIndicatingMetric = ,32
SubsystemComponent = ,33
Abstractness = ,00
PackageMapping = ,75
ExternalAccessesCount = 3,00
AbstractTypesCount = ,00
Instability = ,30
AdherenceToInterfaceCommunication = ,00
DMS = ,70
Coupling = ,00
SliceLayerArchitectureQuality = ,33
TotalTypesCount = 2,00
EfferentCoupling = 7,00
InternalAccessesCount = ,00
NameResemblance = ,42
DirectoryMapping = ,88
DefaultCompositionIndicatingMetric = ,28
InterfaceAccessesCount = ,00
"
graphics
[
targetArrow "standard"
]
]
edge [
source "org.somox.core.subcore2.MySingleClass
"
target "org.somox.core.subcore.MyClass2
"
label "AfferentCoupling = 4,00
DefaultMergeIndicatingMetric = ,30
SubsystemComponent = ,33
Abstractness = ,00
PackageMapping = ,75
ExternalAccessesCount = ,00
AbstractTypesCount = ,00
Instability = ,50
DMS = ,50
AdherenceToInterfaceCommunication = ,00
Coupling = ,00
SliceLayerArchitectureQuality = ,33
TotalTypesCount = 2,00
EfferentCoupling = 4,00
InternalAccessesCount = ,00
NameResemblance = ,20
DirectoryMapping = ,88
DefaultCompositionIndicatingMetric = ,26
InterfaceAccessesCount = ,00
"
graphics
[
targetArrow "standard"
]
]
edge [
source "org.somox.core.subcore.MyClass2
"
target "org.somox.core.subcore2.MySingleClass
"
label "AfferentCoupling = 4,00
DefaultMergeIndicatingMetric = ,30
SubsystemComponent = ,33
Abstractness = ,00
PackageMapping = ,75
ExternalAccessesCount = 4,00
AbstractTypesCount = ,00
Instability = ,50
AdherenceToInterfaceCommunication = ,00
DMS = ,50
Coupling = ,00
SliceLayerArchitectureQuality = ,33
TotalTypesCount = 2,00
EfferentCoupling = 4,00
InternalAccessesCount = ,00
NameResemblance = ,20
DirectoryMapping = ,88
DefaultCompositionIndicatingMetric = ,26
InterfaceAccessesCount = ,00
"
graphics
[
targetArrow "standard"
]
]
edge [
source "org.somox.core.subcore2.MySingleClass
"
target "org.somox.core.subcore.MyClass1
"
label "AfferentCoupling = 7,00
DefaultMergeIndicatingMetric = ,30
SubsystemComponent = ,33
Abstractness = ,00
PackageMapping = ,75
ExternalAccessesCount = ,00
AbstractTypesCount = ,00
Instability = ,36
DMS = ,64
AdherenceToInterfaceCommunication = ,00
Coupling = ,00
SliceLayerArchitectureQuality = ,33
TotalTypesCount = 2,00
EfferentCoupling = 4,00
InternalAccessesCount = ,00
NameResemblance = ,20
DirectoryMapping = ,88
DefaultCompositionIndicatingMetric = ,26
InterfaceAccessesCount = ,00
"
graphics
[
targetArrow "standard"
]
]
edge [
source "org.somox.core.subcore.MyClass1
"
target "org.somox.core.subcore2.MySingleClass
"
label "AfferentCoupling = 7,00
DefaultMergeIndicatingMetric = ,30
SubsystemComponent = ,33
Abstractness = ,00
PackageMapping = ,75
ExternalAccessesCount = 4,00
AbstractTypesCount = ,00
Instability = ,36
AdherenceToInterfaceCommunication = ,00
DMS = ,64
Coupling = ,00
SliceLayerArchitectureQuality = ,33
TotalTypesCount = 2,00
EfferentCoupling = 4,00
InternalAccessesCount = ,00
NameResemblance = ,20
DirectoryMapping = ,88
DefaultCompositionIndicatingMetric = ,26
InterfaceAccessesCount = ,00
"
graphics
[
targetArrow "standard"
]
]
edge [
source "org.somox.core.subcore2.MySingleClass
"
target "org.somox.core.WithCore
"
label "AfferentCoupling = 9,00
DefaultMergeIndicatingMetric = ,33
SubsystemComponent = ,33
Abstractness = ,00
PackageMapping = ,75
ExternalAccessesCount = ,00
AbstractTypesCount = ,00
Instability = ,25
DMS = ,75
AdherenceToInterfaceCommunication = ,00
Coupling = ,00
SliceLayerArchitectureQuality = ,33
TotalTypesCount = 2,00
EfferentCoupling = 3,00
InternalAccessesCount = ,00
NameResemblance = ,42
DirectoryMapping = ,88
DefaultCompositionIndicatingMetric = ,27
InterfaceAccessesCount = ,00
"
graphics
[
targetArrow "standard"
]
]
edge [
source "org.somox.core.WithCore
"
target "org.somox.core.subcore2.MySingleClass
"
label "AfferentCoupling = 9,00
DefaultMergeIndicatingMetric = ,33
SubsystemComponent = ,33
Abstractness = ,00
PackageMapping = ,75
ExternalAccessesCount = 3,00
AbstractTypesCount = ,00
Instability = ,25
AdherenceToInterfaceCommunication = ,00
DMS = ,75
Coupling = ,00
SliceLayerArchitectureQuality = ,33
TotalTypesCount = 2,00
EfferentCoupling = 3,00
InternalAccessesCount = ,00
NameResemblance = ,42
DirectoryMapping = ,88
DefaultCompositionIndicatingMetric = ,27
InterfaceAccessesCount = ,00
"
graphics
[
targetArrow "standard"
]
]
edge [
source "org.somox.core.subcore.sub2.SubSubClass
"
target "org.somox.core.Helper
"
label "AfferentCoupling = 8,00
DefaultMergeIndicatingMetric = ,23
SubsystemComponent = ,00
Abstractness = ,00
PackageMapping = ,60
ExternalAccessesCount = 5,00
AbstractTypesCount = ,00
Instability = ,38
DMS = ,62
AdherenceToInterfaceCommunication = ,00
Coupling = ,00
SliceLayerArchitectureQuality = ,33
TotalTypesCount = 2,00
EfferentCoupling = 5,00
InternalAccessesCount = ,00
NameResemblance = ,00
DirectoryMapping = ,78
DefaultCompositionIndicatingMetric = ,19
InterfaceAccessesCount = ,00
"
graphics
[
targetArrow "standard"
]
]
edge [
source "org.somox.core.Helper
"
target "org.somox.core.subcore.sub2.SubSubClass
"
label "AfferentCoupling = 8,00
DefaultMergeIndicatingMetric = ,23
SubsystemComponent = ,00
Abstractness = ,00
PackageMapping = ,60
ExternalAccessesCount = ,00
AbstractTypesCount = ,00
Instability = ,38
AdherenceToInterfaceCommunication = ,00
DMS = ,62
Coupling = ,00
SliceLayerArchitectureQuality = ,33
TotalTypesCount = 2,00
EfferentCoupling = 5,00
InternalAccessesCount = ,00
NameResemblance = ,00
DirectoryMapping = ,78
DefaultCompositionIndicatingMetric = ,19
InterfaceAccessesCount = ,00
"
graphics
[
targetArrow "standard"
]
]
edge [
source "org.somox.core.Core
org.somox.core.ICoreParent
org.somox.core.IProvided
org.somox.core.ICore
"
target "org.somox.core.Helper
"
label "AfferentCoupling = 7,00
DefaultMergeIndicatingMetric = ,40
SubsystemComponent = ,33
Abstractness = ,60
PackageMapping = 1,00
ExternalAccessesCount = 15,00
AbstractTypesCount = 3,00
Instability = ,61
DMS = ,21
AdherenceToInterfaceCommunication = ,00
Coupling = ,27
SliceLayerArchitectureQuality = ,33
TotalTypesCount = 5,00
EfferentCoupling = 11,00
InternalAccessesCount = 4,00
NameResemblance = ,19
DirectoryMapping = 1,00
DefaultCompositionIndicatingMetric = ,38
InterfaceAccessesCount = ,00
"
graphics
[
targetArrow "standard"
]
]
edge [
source "org.somox.core.Helper
"
target "org.somox.core.Core
org.somox.core.ICoreParent
org.somox.core.IProvided
org.somox.core.ICore
"
label "AfferentCoupling = 7,00
DefaultMergeIndicatingMetric = ,39
SubsystemComponent = ,33
Abstractness = ,60
PackageMapping = 1,00
ExternalAccessesCount = ,00
AbstractTypesCount = 3,00
Instability = ,61
AdherenceToInterfaceCommunication = ,00
DMS = ,21
Coupling = ,00
SliceLayerArchitectureQuality = ,33
TotalTypesCount = 5,00
EfferentCoupling = 11,00
InternalAccessesCount = ,00
NameResemblance = ,19
DirectoryMapping = 1,00
DefaultCompositionIndicatingMetric = ,36
InterfaceAccessesCount = ,00
"
graphics
[
targetArrow "standard"
]
]
edge [
source "org.somox.core.Helper
"
target "org.somox.core.WithCore
"
label "AfferentCoupling = 17,00
DefaultMergeIndicatingMetric = ,41
SubsystemComponent = ,33
Abstractness = ,00
PackageMapping = 1,00
ExternalAccessesCount = ,00
AbstractTypesCount = ,00
Instability = ,15
DMS = ,85
AdherenceToInterfaceCommunication = ,00
Coupling = ,00
SliceLayerArchitectureQuality = ,33
TotalTypesCount = 2,00
EfferentCoupling = 3,00
InternalAccessesCount = ,00
NameResemblance = ,36
DirectoryMapping = 1,00
DefaultCompositionIndicatingMetric = ,35
InterfaceAccessesCount = ,00
"
graphics
[
targetArrow "standard"
]
]
edge [
source "org.somox.core.WithCore
"
target "org.somox.core.Helper
"
label "AfferentCoupling = 17,00
DefaultMergeIndicatingMetric = ,41
SubsystemComponent = ,33
Abstractness = ,00
PackageMapping = 1,00
ExternalAccessesCount = 3,00
AbstractTypesCount = ,00
Instability = ,15
AdherenceToInterfaceCommunication = ,00
DMS = ,85
Coupling = ,00
SliceLayerArchitectureQuality = ,33
TotalTypesCount = 2,00
EfferentCoupling = 3,00
InternalAccessesCount = ,00
NameResemblance = ,36
DirectoryMapping = 1,00
DefaultCompositionIndicatingMetric = ,35
InterfaceAccessesCount = ,00
"
graphics
[
targetArrow "standard"
]
]
edge [
source "org.somox.core.subcore.sub2.SubSubClass
"
target "org.somox.core.subcore2.MySingleClass
"
label "AfferentCoupling = ,00
DefaultMergeIndicatingMetric = ,25
SubsystemComponent = ,00
Abstractness = ,00
PackageMapping = ,60
ExternalAccessesCount = 5,00
AbstractTypesCount = ,00
Instability = 1,00
DMS = ,00
AdherenceToInterfaceCommunication = ,00
Coupling = ,00
SliceLayerArchitectureQuality = 1,00
TotalTypesCount = 2,00
EfferentCoupling = 5,00
InternalAccessesCount = ,00
NameResemblance = ,61
DirectoryMapping = ,78
DefaultCompositionIndicatingMetric = ,24
InterfaceAccessesCount = ,00
"
graphics
[
targetArrow "standard"
]
]
edge [
source "org.somox.core.subcore2.MySingleClass
"
target "org.somox.core.subcore.sub2.SubSubClass
"
label "AfferentCoupling = ,00
DefaultMergeIndicatingMetric = ,25
SubsystemComponent = ,00
Abstractness = ,00
PackageMapping = ,60
ExternalAccessesCount = ,00
AbstractTypesCount = ,00
Instability = 1,00
AdherenceToInterfaceCommunication = ,00
DMS = ,00
Coupling = ,00
SliceLayerArchitectureQuality = 1,00
TotalTypesCount = 2,00
EfferentCoupling = 5,00
InternalAccessesCount = ,00
NameResemblance = ,61
DirectoryMapping = ,78
DefaultCompositionIndicatingMetric = ,24
InterfaceAccessesCount = ,00
"
graphics
[
targetArrow "standard"
]
]
edge [
source "org.somox.core.Core
org.somox.core.ICoreParent
org.somox.core.IProvided
org.somox.core.ICore
"
target "org.somox.core.subcore2.MySingleClass
"
label "AfferentCoupling = 3,00
DefaultMergeIndicatingMetric = ,30
SubsystemComponent = ,33
Abstractness = ,60
PackageMapping = ,75
ExternalAccessesCount = 15,00
AbstractTypesCount = 3,00
Instability = ,83
DMS = ,43
AdherenceToInterfaceCommunication = ,00
Coupling = ,00
SliceLayerArchitectureQuality = ,33
TotalTypesCount = 5,00
EfferentCoupling = 15,00
InternalAccessesCount = ,00
NameResemblance = ,21
DirectoryMapping = ,88
DefaultCompositionIndicatingMetric = ,27
InterfaceAccessesCount = ,00
"
graphics
[
targetArrow "standard"
]
]
edge [
source "org.somox.core.subcore2.MySingleClass
"
target "org.somox.core.Core
org.somox.core.ICoreParent
org.somox.core.IProvided
org.somox.core.ICore
"
label "AfferentCoupling = 3,00
DefaultMergeIndicatingMetric = ,30
SubsystemComponent = ,33
Abstractness = ,60
PackageMapping = ,75
ExternalAccessesCount = ,00
AbstractTypesCount = 3,00
Instability = ,83
AdherenceToInterfaceCommunication = ,00
DMS = ,43
Coupling = ,00
SliceLayerArchitectureQuality = ,33
TotalTypesCount = 5,00
EfferentCoupling = 15,00
InternalAccessesCount = ,00
NameResemblance = ,21
DirectoryMapping = ,88
DefaultCompositionIndicatingMetric = ,27
InterfaceAccessesCount = ,00
"
graphics
[
targetArrow "standard"
]
]
edge [
source "org.somox.core.subcore.MyClass2
"
target "org.somox.core.Helper
"
label "AfferentCoupling = 10,00
DefaultMergeIndicatingMetric = ,36
SubsystemComponent = ,33
Abstractness = ,00
PackageMapping = ,75
ExternalAccessesCount = 4,00
AbstractTypesCount = ,00
Instability = ,17
DMS = ,83
AdherenceToInterfaceCommunication = ,00
Coupling = ,50
SliceLayerArchitectureQuality = ,33
TotalTypesCount = 2,00
EfferentCoupling = 2,00
InternalAccessesCount = 2,00
NameResemblance = ,43
DirectoryMapping = ,88
DefaultCompositionIndicatingMetric = ,30
InterfaceAccessesCount = ,00
"
graphics
[
targetArrow "standard"
]
]
edge [
source "org.somox.core.Helper
"
target "org.somox.core.subcore.MyClass2
"
label "AfferentCoupling = 10,00
DefaultMergeIndicatingMetric = ,33
SubsystemComponent = ,33
Abstractness = ,00
PackageMapping = ,75
ExternalAccessesCount = ,00
AbstractTypesCount = ,00
Instability = ,17
AdherenceToInterfaceCommunication = ,00
DMS = ,83
Coupling = ,00
SliceLayerArchitectureQuality = ,33
TotalTypesCount = 2,00
EfferentCoupling = 2,00
InternalAccessesCount = ,00
NameResemblance = ,43
DirectoryMapping = ,88
DefaultCompositionIndicatingMetric = ,27
InterfaceAccessesCount = ,00
"
graphics
[
targetArrow "standard"
]
]
edge [
source "org.somox.core.subcore.MyClass1
"
target "org.somox.core.Helper
"
label "AfferentCoupling = 13,00
DefaultMergeIndicatingMetric = ,36
SubsystemComponent = ,33
Abstractness = ,00
PackageMapping = ,75
ExternalAccessesCount = 4,00
AbstractTypesCount = ,00
Instability = ,13
DMS = ,87
AdherenceToInterfaceCommunication = ,00
Coupling = ,50
SliceLayerArchitectureQuality = ,33
TotalTypesCount = 2,00
EfferentCoupling = 2,00
InternalAccessesCount = 2,00
NameResemblance = ,43
DirectoryMapping = ,88
DefaultCompositionIndicatingMetric = ,30
InterfaceAccessesCount = ,00
"
graphics
[
targetArrow "standard"
]
]
edge [
source "org.somox.core.Helper
"
target "org.somox.core.subcore.MyClass1
"
label "AfferentCoupling = 13,00
DefaultMergeIndicatingMetric = ,33
SubsystemComponent = ,33
Abstractness = ,00
PackageMapping = ,75
ExternalAccessesCount = ,00
AbstractTypesCount = ,00
Instability = ,13
AdherenceToInterfaceCommunication = ,00
DMS = ,87
Coupling = ,00
SliceLayerArchitectureQuality = ,33
TotalTypesCount = 2,00
EfferentCoupling = 2,00
InternalAccessesCount = ,00
NameResemblance = ,43
DirectoryMapping = ,88
DefaultCompositionIndicatingMetric = ,27
InterfaceAccessesCount = ,00
"
graphics
[
targetArrow "standard"
]
]
edge [
source "org.somox.core.subcore.sub2.SubSubClass
"
target "org.somox.core.subcore.MyClass1
"
label "AfferentCoupling = 4,00
DefaultMergeIndicatingMetric = ,36
SubsystemComponent = ,00
Abstractness = ,00
PackageMapping = ,80
ExternalAccessesCount = 5,00
AbstractTypesCount = ,00
Instability = ,60
DMS = ,40
AdherenceToInterfaceCommunication = ,00
Coupling = ,60
SliceLayerArchitectureQuality = 1,00
TotalTypesCount = 2,00
EfferentCoupling = 6,00
InternalAccessesCount = 3,00
NameResemblance = ,69
DirectoryMapping = ,89
DefaultCompositionIndicatingMetric = ,33
InterfaceAccessesCount = ,00
"
graphics
[
targetArrow "standard"
]
]
edge [
source "org.somox.core.subcore.MyClass1
"
target "org.somox.core.subcore.sub2.SubSubClass
"
label "AfferentCoupling = 4,00
DefaultMergeIndicatingMetric = ,32
SubsystemComponent = ,00
Abstractness = ,00
PackageMapping = ,80
ExternalAccessesCount = 4,00
AbstractTypesCount = ,00
Instability = ,60
AdherenceToInterfaceCommunication = ,00
DMS = ,40
Coupling = ,00
SliceLayerArchitectureQuality = 1,00
TotalTypesCount = 2,00
EfferentCoupling = 6,00
InternalAccessesCount = ,00
NameResemblance = ,69
DirectoryMapping = ,89
DefaultCompositionIndicatingMetric = ,29
InterfaceAccessesCount = ,00
"
graphics
[
targetArrow "standard"
]
]
edge [
source "org.somox.core.subcore.sub2.SubSubClass
"
target "org.somox.core.subcore.MyClass2
"
label "AfferentCoupling = 2,00
DefaultMergeIndicatingMetric = ,36
SubsystemComponent = ,00
Abstractness = ,00
PackageMapping = ,80
ExternalAccessesCount = 5,00
AbstractTypesCount = ,00
Instability = ,78
DMS = ,22
AdherenceToInterfaceCommunication = ,00
Coupling = ,40
SliceLayerArchitectureQuality = 1,00
TotalTypesCount = 2,00
EfferentCoupling = 7,00
InternalAccessesCount = 2,00
NameResemblance = ,69
DirectoryMapping = ,89
DefaultCompositionIndicatingMetric = ,33
InterfaceAccessesCount = ,00
"
graphics
[
targetArrow "standard"
]
]
edge [
source "org.somox.core.subcore.MyClass2
"
target "org.somox.core.subcore.sub2.SubSubClass
"
label "AfferentCoupling = 2,00
DefaultMergeIndicatingMetric = ,32
SubsystemComponent = ,00
Abstractness = ,00
PackageMapping = ,80
ExternalAccessesCount = 4,00
AbstractTypesCount = ,00
Instability = ,78
AdherenceToInterfaceCommunication = ,00
DMS = ,22
Coupling = ,00
SliceLayerArchitectureQuality = 1,00
TotalTypesCount = 2,00
EfferentCoupling = 7,00
InternalAccessesCount = ,00
NameResemblance = ,69
DirectoryMapping = ,89
DefaultCompositionIndicatingMetric = ,29
InterfaceAccessesCount = ,00
"
graphics
[
targetArrow "standard"
]
]
edge [
source "org.somox.core.Core
org.somox.core.ICoreParent
org.somox.core.IProvided
org.somox.core.ICore
"
target "org.somox.core.subcore.MyClass1
"
label "AfferentCoupling = 8,00
DefaultMergeIndicatingMetric = ,32
SubsystemComponent = ,33
Abstractness = ,60
PackageMapping = ,75
ExternalAccessesCount = 15,00
AbstractTypesCount = 3,00
Instability = ,68
DMS = ,28
AdherenceToInterfaceCommunication = ,00
Coupling = ,13
SliceLayerArchitectureQuality = ,33
TotalTypesCount = 5,00
EfferentCoupling = 17,00
InternalAccessesCount = 2,00
NameResemblance = ,34
DirectoryMapping = ,88
DefaultCompositionIndicatingMetric = ,29
InterfaceAccessesCount = ,00
"
graphics
[
targetArrow "standard"
]
]
edge [
source "org.somox.core.subcore.MyClass1
"
target "org.somox.core.Core
org.somox.core.ICoreParent
org.somox.core.IProvided
org.somox.core.ICore
"
label "AfferentCoupling = 8,00
DefaultMergeIndicatingMetric = ,32
SubsystemComponent = ,33
Abstractness = ,60
PackageMapping = ,75
ExternalAccessesCount = 4,00
AbstractTypesCount = 3,00
Instability = ,68
AdherenceToInterfaceCommunication = ,00
DMS = ,28
Coupling = ,00
SliceLayerArchitectureQuality = ,33
TotalTypesCount = 5,00
EfferentCoupling = 17,00
InternalAccessesCount = ,00
NameResemblance = ,34
DirectoryMapping = ,88
DefaultCompositionIndicatingMetric = ,29
InterfaceAccessesCount = ,00
"
graphics
[
targetArrow "standard"
]
]
edge [
source "org.somox.core.Core
org.somox.core.ICoreParent
org.somox.core.IProvided
org.somox.core.ICore
"
target "org.somox.core.subcore.MyClass2
"
label "AfferentCoupling = 7,00
DefaultMergeIndicatingMetric = ,32
SubsystemComponent = ,33
Abstractness = ,60
PackageMapping = ,75
ExternalAccessesCount = 15,00
AbstractTypesCount = 3,00
Instability = ,73
DMS = ,33
AdherenceToInterfaceCommunication = ,00
Coupling = ,00
SliceLayerArchitectureQuality = ,33
TotalTypesCount = 5,00
EfferentCoupling = 19,00
InternalAccessesCount = ,00
NameResemblance = ,34
DirectoryMapping = ,88
DefaultCompositionIndicatingMetric = ,29
InterfaceAccessesCount = ,00
"
graphics
[
targetArrow "standard"
]
]
edge [
source "org.somox.core.subcore.MyClass2
"
target "org.somox.core.Core
org.somox.core.ICoreParent
org.somox.core.IProvided
org.somox.core.ICore
"
label "AfferentCoupling = 7,00
DefaultMergeIndicatingMetric = ,32
SubsystemComponent = ,33
Abstractness = ,60
PackageMapping = ,75
ExternalAccessesCount = 4,00
AbstractTypesCount = 3,00
Instability = ,73
AdherenceToInterfaceCommunication = ,00
DMS = ,33
Coupling = ,00
SliceLayerArchitectureQuality = ,33
TotalTypesCount = 5,00
EfferentCoupling = 19,00
InternalAccessesCount = ,00
NameResemblance = ,34
DirectoryMapping = ,88
DefaultCompositionIndicatingMetric = ,29
InterfaceAccessesCount = ,00
"
graphics
[
targetArrow "standard"
]
]
edge [
source "org.somox.core.subcore2.MySingleClass
"
target "org.somox.core.Helper
"
label "AfferentCoupling = 8,00
DefaultMergeIndicatingMetric = ,28
SubsystemComponent = ,33
Abstractness = ,00
PackageMapping = ,75
ExternalAccessesCount = ,00
AbstractTypesCount = ,00
Instability = ,00
DMS = 1,00
AdherenceToInterfaceCommunication = ,00
Coupling = ,00
SliceLayerArchitectureQuality = ,33
TotalTypesCount = 2,00
EfferentCoupling = ,00
InternalAccessesCount = ,00
NameResemblance = ,00
DirectoryMapping = ,88
DefaultCompositionIndicatingMetric = ,22
InterfaceAccessesCount = ,00
"
graphics
[
targetArrow "standard"
]
]
edge [
source "org.somox.core.Helper
"
target "org.somox.core.subcore2.MySingleClass
"
label "AfferentCoupling = 8,00
DefaultMergeIndicatingMetric = ,28
SubsystemComponent = ,33
Abstractness = ,00
PackageMapping = ,75
ExternalAccessesCount = ,00
AbstractTypesCount = ,00
Instability = ,00
AdherenceToInterfaceCommunication = ,00
DMS = 1,00
Coupling = ,00
SliceLayerArchitectureQuality = ,33
TotalTypesCount = 2,00
EfferentCoupling = ,00
InternalAccessesCount = ,00
NameResemblance = ,00
DirectoryMapping = ,88
DefaultCompositionIndicatingMetric = ,22
InterfaceAccessesCount = ,00
"
graphics
[
targetArrow "standard"
]
]
edge [
source "org.somox.core.subcore.sub2.SubSubClass
"
target "org.somox.core.WithCore
"
label "AfferentCoupling = 9,00
DefaultMergeIndicatingMetric = ,27
SubsystemComponent = ,00
Abstractness = ,00
PackageMapping = ,60
ExternalAccessesCount = 5,00
AbstractTypesCount = ,00
Instability = ,47
DMS = ,53
AdherenceToInterfaceCommunication = ,00
Coupling = ,00
SliceLayerArchitectureQuality = ,33
TotalTypesCount = 2,00
EfferentCoupling = 8,00
InternalAccessesCount = ,00
NameResemblance = ,41
DirectoryMapping = ,78
DefaultCompositionIndicatingMetric = ,23
InterfaceAccessesCount = ,00
"
graphics
[
targetArrow "standard"
]
]
edge [
source "org.somox.core.WithCore
"
target "org.somox.core.subcore.sub2.SubSubClass
"
label "AfferentCoupling = 9,00
DefaultMergeIndicatingMetric = ,27
SubsystemComponent = ,00
Abstractness = ,00
PackageMapping = ,60
ExternalAccessesCount = 3,00
AbstractTypesCount = ,00
Instability = ,47
AdherenceToInterfaceCommunication = ,00
DMS = ,53
Coupling = ,00
SliceLayerArchitectureQuality = ,33
TotalTypesCount = 2,00
EfferentCoupling = 8,00
InternalAccessesCount = ,00
NameResemblance = ,41
DirectoryMapping = ,78
DefaultCompositionIndicatingMetric = ,23
InterfaceAccessesCount = ,00
"
graphics
[
targetArrow "standard"
]
]
edge [
source "org.somox.core.Core
org.somox.core.ICoreParent
org.somox.core.IProvided
org.somox.core.ICore
"
target "org.somox.core.WithCore
"
label "AfferentCoupling = ,00
DefaultMergeIndicatingMetric = ,42
SubsystemComponent = ,33
Abstractness = ,60
PackageMapping = 1,00
ExternalAccessesCount = 15,00
AbstractTypesCount = 3,00
Instability = 1,00
DMS = ,60
AdherenceToInterfaceCommunication = ,00
Coupling = ,60
SliceLayerArchitectureQuality = ,33
TotalTypesCount = 5,00
EfferentCoupling = 6,00
InternalAccessesCount = 9,00
NameResemblance = ,30
DirectoryMapping = 1,00
DefaultCompositionIndicatingMetric = ,37
InterfaceAccessesCount = ,00
"
graphics
[
targetArrow "standard"
]
]
edge [
source "org.somox.core.WithCore
"
target "org.somox.core.Core
org.somox.core.ICoreParent
org.somox.core.IProvided
org.somox.core.ICore
"
label "AfferentCoupling = ,00
DefaultMergeIndicatingMetric = ,56
SubsystemComponent = ,33
Abstractness = ,60
PackageMapping = 1,00
ExternalAccessesCount = 3,00
AbstractTypesCount = 3,00
Instability = 1,00
AdherenceToInterfaceCommunication = ,00
DMS = ,60
Coupling = 1,00
SliceLayerArchitectureQuality = ,33
TotalTypesCount = 5,00
EfferentCoupling = 6,00
InternalAccessesCount = 3,00
NameResemblance = ,30
DirectoryMapping = 1,00
DefaultCompositionIndicatingMetric = ,50
InterfaceAccessesCount = ,00
"
graphics
[
targetArrow "standard"
]
]
]
