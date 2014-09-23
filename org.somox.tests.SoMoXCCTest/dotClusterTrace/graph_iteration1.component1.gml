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
