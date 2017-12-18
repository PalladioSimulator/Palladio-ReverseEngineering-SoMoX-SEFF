graph [
directed 1
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
node [
name "org.somox.core.WithCore
org.somox.core.Core
org.somox.core.Helper
org.somox.core.ICoreParent
org.somox.core.IProvided
org.somox.core.ICore
"
label "org.somox.core.WithCore
org.somox.core.Core
org.somox.core.Helper
org.somox.core.ICoreParent
org.somox.core.IProvided
org.somox.core.ICore
"
graphics
[hasFill 0
]
]
node [
name "org.somox.core.subcore.MyClass1
org.somox.core.subcore.MyClass2
"
label "org.somox.core.subcore.MyClass1
org.somox.core.subcore.MyClass2
"
graphics
[hasFill 0
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
source "org.somox.core.subcore.MyClass1
org.somox.core.subcore.MyClass2
"
target "org.somox.core.WithCore
org.somox.core.Core
org.somox.core.Helper
org.somox.core.ICoreParent
org.somox.core.IProvided
org.somox.core.ICore
"
label "AfferentCoupling = 5,00
DefaultMergeIndicatingMetric = ,51
SubsystemComponent = ,33
Abstractness = ,38
PackageMapping = ,75
ExternalAccessesCount = 4,00
AbstractTypesCount = 3,00
Instability = ,00
DMS = ,62
AdherenceToInterfaceCommunication = ,00
Coupling = 1,00
SliceLayerArchitectureQuality = ,33
TotalTypesCount = 8,00
EfferentCoupling = ,00
InternalAccessesCount = 4,00
NameResemblance = ,37
DirectoryMapping = ,88
DefaultCompositionIndicatingMetric = ,46
InterfaceAccessesCount = ,00
"
graphics
[
targetArrow "standard"
]
]
edge [
source "org.somox.core.WithCore
org.somox.core.Core
org.somox.core.Helper
org.somox.core.ICoreParent
org.somox.core.IProvided
org.somox.core.ICore
"
target "org.somox.core.subcore.MyClass1
org.somox.core.subcore.MyClass2
"
label "AfferentCoupling = 5,00
DefaultMergeIndicatingMetric = ,51
SubsystemComponent = ,33
Abstractness = ,38
PackageMapping = ,75
ExternalAccessesCount = 2,00
AbstractTypesCount = 3,00
Instability = ,00
AdherenceToInterfaceCommunication = ,00
DMS = ,62
Coupling = 1,00
SliceLayerArchitectureQuality = ,33
TotalTypesCount = 8,00
EfferentCoupling = ,00
InternalAccessesCount = 2,00
NameResemblance = ,37
DirectoryMapping = ,88
DefaultCompositionIndicatingMetric = ,46
InterfaceAccessesCount = ,00
"
graphics
[
targetArrow "standard"
]
]
edge [
source "org.somox.core.WithCore
org.somox.core.Core
org.somox.core.Helper
org.somox.core.ICoreParent
org.somox.core.IProvided
org.somox.core.ICore
"
target "org.somox.core.subcore2.MySingleClass
"
label "AfferentCoupling = 4,00
DefaultMergeIndicatingMetric = ,30
SubsystemComponent = ,33
Abstractness = ,43
PackageMapping = ,75
ExternalAccessesCount = 2,00
AbstractTypesCount = 3,00
Instability = ,33
DMS = ,24
AdherenceToInterfaceCommunication = ,00
Coupling = ,00
SliceLayerArchitectureQuality = ,33
TotalTypesCount = 7,00
EfferentCoupling = 2,00
InternalAccessesCount = ,00
NameResemblance = ,21
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
target "org.somox.core.WithCore
org.somox.core.Core
org.somox.core.Helper
org.somox.core.ICoreParent
org.somox.core.IProvided
org.somox.core.ICore
"
label "AfferentCoupling = 4,00
DefaultMergeIndicatingMetric = ,30
SubsystemComponent = ,33
Abstractness = ,43
PackageMapping = ,75
ExternalAccessesCount = ,00
AbstractTypesCount = 3,00
Instability = ,33
AdherenceToInterfaceCommunication = ,00
DMS = ,24
Coupling = ,00
SliceLayerArchitectureQuality = ,33
TotalTypesCount = 7,00
EfferentCoupling = 2,00
InternalAccessesCount = ,00
NameResemblance = ,21
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
org.somox.core.subcore.MyClass2
"
target "org.somox.core.subcore2.MySingleClass
"
label "AfferentCoupling = 7,00
DefaultMergeIndicatingMetric = ,26
SubsystemComponent = ,00
Abstractness = ,00
PackageMapping = ,75
ExternalAccessesCount = 4,00
AbstractTypesCount = ,00
Instability = ,36
DMS = ,64
AdherenceToInterfaceCommunication = ,00
Coupling = ,00
SliceLayerArchitectureQuality = 1,00
TotalTypesCount = 3,00
EfferentCoupling = 4,00
InternalAccessesCount = ,00
NameResemblance = ,20
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
source "org.somox.core.subcore2.MySingleClass
"
target "org.somox.core.subcore.MyClass1
org.somox.core.subcore.MyClass2
"
label "AfferentCoupling = 7,00
DefaultMergeIndicatingMetric = ,26
SubsystemComponent = ,00
Abstractness = ,00
PackageMapping = ,75
ExternalAccessesCount = ,00
AbstractTypesCount = ,00
Instability = ,36
AdherenceToInterfaceCommunication = ,00
DMS = ,64
Coupling = ,00
SliceLayerArchitectureQuality = 1,00
TotalTypesCount = 3,00
EfferentCoupling = 4,00
InternalAccessesCount = ,00
NameResemblance = ,20
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
source "org.somox.core.subcore.MyClass1
org.somox.core.subcore.MyClass2
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
Instability = ,67
DMS = ,33
AdherenceToInterfaceCommunication = ,00
Coupling = ,00
SliceLayerArchitectureQuality = 1,00
TotalTypesCount = 3,00
EfferentCoupling = 4,00
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
target "org.somox.core.subcore.MyClass1
org.somox.core.subcore.MyClass2
"
label "AfferentCoupling = 2,00
DefaultMergeIndicatingMetric = ,63
SubsystemComponent = ,00
Abstractness = ,00
PackageMapping = ,80
ExternalAccessesCount = 5,00
AbstractTypesCount = ,00
Instability = ,67
AdherenceToInterfaceCommunication = ,00
DMS = ,33
Coupling = 1,00
SliceLayerArchitectureQuality = 1,00
TotalTypesCount = 3,00
EfferentCoupling = 4,00
InternalAccessesCount = 5,00
NameResemblance = ,69
DirectoryMapping = ,89
DefaultCompositionIndicatingMetric = ,59
InterfaceAccessesCount = ,00
"
graphics
[
targetArrow "standard"
]
]
edge [
source "org.somox.core.WithCore
org.somox.core.Core
org.somox.core.Helper
org.somox.core.ICoreParent
org.somox.core.IProvided
org.somox.core.ICore
"
target "org.somox.core.subcore.sub2.SubSubClass
"
label "AfferentCoupling = 4,00
DefaultMergeIndicatingMetric = ,24
SubsystemComponent = ,33
Abstractness = ,43
PackageMapping = ,60
ExternalAccessesCount = 2,00
AbstractTypesCount = 3,00
Instability = ,64
DMS = ,06
AdherenceToInterfaceCommunication = ,00
Coupling = ,00
SliceLayerArchitectureQuality = ,33
TotalTypesCount = 7,00
EfferentCoupling = 7,00
InternalAccessesCount = ,00
NameResemblance = ,14
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
source "org.somox.core.subcore.sub2.SubSubClass
"
target "org.somox.core.WithCore
org.somox.core.Core
org.somox.core.Helper
org.somox.core.ICoreParent
org.somox.core.IProvided
org.somox.core.ICore
"
label "AfferentCoupling = 4,00
DefaultMergeIndicatingMetric = ,24
SubsystemComponent = ,33
Abstractness = ,43
PackageMapping = ,60
ExternalAccessesCount = 5,00
AbstractTypesCount = 3,00
Instability = ,64
AdherenceToInterfaceCommunication = ,00
DMS = ,06
Coupling = ,00
SliceLayerArchitectureQuality = ,33
TotalTypesCount = 7,00
EfferentCoupling = 7,00
InternalAccessesCount = ,00
NameResemblance = ,14
DirectoryMapping = ,78
DefaultCompositionIndicatingMetric = ,23
InterfaceAccessesCount = ,00
"
graphics
[
targetArrow "standard"
]
]
]
