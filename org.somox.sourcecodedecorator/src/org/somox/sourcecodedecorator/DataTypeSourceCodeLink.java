/**
 */
package org.somox.sourcecodedecorator;

import org.palladiosimulator.pcm.repository.DataType;

import org.eclipse.emf.common.util.EList;
import org.emftext.language.java.types.Type;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Data Type Source Code Link</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.somox.sourcecodedecorator.DataTypeSourceCodeLink#getJaMoPPType <em>Ja Mo PP Type</em>}</li>
 *   <li>{@link org.somox.sourcecodedecorator.DataTypeSourceCodeLink#getPcmDataType <em>Pcm Data Type</em>}</li>
 *   <li>{@link org.somox.sourcecodedecorator.DataTypeSourceCodeLink#getInnerDatatypeSourceCodeLink <em>Inner Datatype Source Code Link</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.somox.sourcecodedecorator.SourcecodedecoratorPackage#getDataTypeSourceCodeLink()
 * @model
 * @generated
 */
public interface DataTypeSourceCodeLink extends FileLevelSourceCodeLink {
    /**
     * Returns the value of the '<em><b>Ja Mo PP Type</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Ja Mo PP Type</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Ja Mo PP Type</em>' reference.
     * @see #setJaMoPPType(Type)
     * @see org.somox.sourcecodedecorator.SourcecodedecoratorPackage#getDataTypeSourceCodeLink_JaMoPPType()
     * @model
     * @generated
     */
    Type getJaMoPPType();

    /**
     * Sets the value of the '{@link org.somox.sourcecodedecorator.DataTypeSourceCodeLink#getJaMoPPType <em>Ja Mo PP Type</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Ja Mo PP Type</em>' reference.
     * @see #getJaMoPPType()
     * @generated
     */
    void setJaMoPPType(Type value);

    /**
     * Returns the value of the '<em><b>Pcm Data Type</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Pcm Data Type</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Pcm Data Type</em>' reference.
     * @see #setPcmDataType(DataType)
     * @see org.somox.sourcecodedecorator.SourcecodedecoratorPackage#getDataTypeSourceCodeLink_PcmDataType()
     * @model
     * @generated
     */
    DataType getPcmDataType();

    /**
     * Sets the value of the '{@link org.somox.sourcecodedecorator.DataTypeSourceCodeLink#getPcmDataType <em>Pcm Data Type</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Pcm Data Type</em>' reference.
     * @see #getPcmDataType()
     * @generated
     */
    void setPcmDataType(DataType value);

    /**
     * Returns the value of the '<em><b>Inner Datatype Source Code Link</b></em>' containment reference list.
     * The list contents are of type {@link org.somox.sourcecodedecorator.InnerDatatypeSourceCodeLink}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Inner Datatype Source Code Link</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Inner Datatype Source Code Link</em>' containment reference list.
     * @see org.somox.sourcecodedecorator.SourcecodedecoratorPackage#getDataTypeSourceCodeLink_InnerDatatypeSourceCodeLink()
     * @model containment="true"
     * @generated
     */
    EList<InnerDatatypeSourceCodeLink> getInnerDatatypeSourceCodeLink();

} // DataTypeSourceCodeLink
