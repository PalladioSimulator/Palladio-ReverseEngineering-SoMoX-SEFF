/**
 */
package org.somox.sourcecodedecorator.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.emftext.language.java.containers.CompilationUnit;
import org.palladiosimulator.pcm.repository.RepositoryComponent;
import org.somox.sourcecodedecorator.FileLevelSourceCodeLink;
import org.somox.sourcecodedecorator.SourcecodedecoratorPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>File Level Source Code Link</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.somox.sourcecodedecorator.impl.FileLevelSourceCodeLinkImpl#getRepositoryComponent <em>Repository Component</em>}</li>
 *   <li>{@link org.somox.sourcecodedecorator.impl.FileLevelSourceCodeLinkImpl#getFile <em>File</em>}</li>
 * </ul>
 *
 * @generated
 */
public class FileLevelSourceCodeLinkImpl extends MinimalEObjectImpl.Container implements FileLevelSourceCodeLink {
    /**
     * The cached value of the '{@link #getRepositoryComponent() <em>Repository Component</em>}' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getRepositoryComponent()
     * @generated
     * @ordered
     */
    protected RepositoryComponent repositoryComponent;

    /**
     * The cached value of the '{@link #getFile() <em>File</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getFile()
     * @generated
     * @ordered
     */
    protected CompilationUnit file;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected FileLevelSourceCodeLinkImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return SourcecodedecoratorPackage.Literals.FILE_LEVEL_SOURCE_CODE_LINK;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public RepositoryComponent getRepositoryComponent() {
        if (repositoryComponent != null && ((EObject)repositoryComponent).eIsProxy()) {
            InternalEObject oldRepositoryComponent = (InternalEObject)repositoryComponent;
            repositoryComponent = (RepositoryComponent)eResolveProxy(oldRepositoryComponent);
            if (repositoryComponent != oldRepositoryComponent) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, SourcecodedecoratorPackage.FILE_LEVEL_SOURCE_CODE_LINK__REPOSITORY_COMPONENT, oldRepositoryComponent, repositoryComponent));
            }
        }
        return repositoryComponent;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public RepositoryComponent basicGetRepositoryComponent() {
        return repositoryComponent;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setRepositoryComponent(RepositoryComponent newRepositoryComponent) {
        RepositoryComponent oldRepositoryComponent = repositoryComponent;
        repositoryComponent = newRepositoryComponent;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SourcecodedecoratorPackage.FILE_LEVEL_SOURCE_CODE_LINK__REPOSITORY_COMPONENT, oldRepositoryComponent, repositoryComponent));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public CompilationUnit getFile() {
        if (file != null && file.eIsProxy()) {
            InternalEObject oldFile = (InternalEObject)file;
            file = (CompilationUnit)eResolveProxy(oldFile);
            if (file != oldFile) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, SourcecodedecoratorPackage.FILE_LEVEL_SOURCE_CODE_LINK__FILE, oldFile, file));
            }
        }
        return file;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public CompilationUnit basicGetFile() {
        return file;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setFile(CompilationUnit newFile) {
        CompilationUnit oldFile = file;
        file = newFile;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SourcecodedecoratorPackage.FILE_LEVEL_SOURCE_CODE_LINK__FILE, oldFile, file));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case SourcecodedecoratorPackage.FILE_LEVEL_SOURCE_CODE_LINK__REPOSITORY_COMPONENT:
                if (resolve) return getRepositoryComponent();
                return basicGetRepositoryComponent();
            case SourcecodedecoratorPackage.FILE_LEVEL_SOURCE_CODE_LINK__FILE:
                if (resolve) return getFile();
                return basicGetFile();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case SourcecodedecoratorPackage.FILE_LEVEL_SOURCE_CODE_LINK__REPOSITORY_COMPONENT:
                setRepositoryComponent((RepositoryComponent)newValue);
                return;
            case SourcecodedecoratorPackage.FILE_LEVEL_SOURCE_CODE_LINK__FILE:
                setFile((CompilationUnit)newValue);
                return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eUnset(int featureID) {
        switch (featureID) {
            case SourcecodedecoratorPackage.FILE_LEVEL_SOURCE_CODE_LINK__REPOSITORY_COMPONENT:
                setRepositoryComponent((RepositoryComponent)null);
                return;
            case SourcecodedecoratorPackage.FILE_LEVEL_SOURCE_CODE_LINK__FILE:
                setFile((CompilationUnit)null);
                return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean eIsSet(int featureID) {
        switch (featureID) {
            case SourcecodedecoratorPackage.FILE_LEVEL_SOURCE_CODE_LINK__REPOSITORY_COMPONENT:
                return repositoryComponent != null;
            case SourcecodedecoratorPackage.FILE_LEVEL_SOURCE_CODE_LINK__FILE:
                return file != null;
        }
        return super.eIsSet(featureID);
    }

} // FileLevelSourceCodeLinkImpl
