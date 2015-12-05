/**
 */
package org.somox.seff2javaast;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc --> The <b>Package</b> for the model. It contains accessors for the meta
 * objects to represent
 * <ul>
 * <li>each class,</li>
 * <li>each feature of each class,</li>
 * <li>each operation of each class,</li>
 * <li>each enum,</li>
 * <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 *
 * @see org.somox.seff2javaast.Seff2javaastFactory
 * @model kind="package"
 * @generated
 */
public interface Seff2javaastPackage extends EPackage {
    /**
     * The package name. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    String eNAME = "seff2javaast";

    /**
     * The package namespace URI. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    String eNS_URI = "http://www.somox.org/seff2method/1.0";

    /**
     * The package namespace name. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    String eNS_PREFIX = "org.somox";

    /**
     * The singleton instance of the package. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    Seff2javaastPackage eINSTANCE = org.somox.seff2javaast.impl.Seff2javaastPackageImpl.init();

    /**
     * The meta object id for the '{@link org.somox.seff2javaast.impl.SEFF2MethodMappingImpl
     * <em>SEFF2 Method Mapping</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.somox.seff2javaast.impl.SEFF2MethodMappingImpl
     * @see org.somox.seff2javaast.impl.Seff2javaastPackageImpl#getSEFF2MethodMapping()
     * @generated
     */
    int SEFF2_METHOD_MAPPING = 0;

    /**
     * The feature id for the '<em><b>Blockstatement</b></em>' reference. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SEFF2_METHOD_MAPPING__BLOCKSTATEMENT = 0;

    /**
     * The feature id for the '<em><b>Seff</b></em>' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SEFF2_METHOD_MAPPING__SEFF = 1;

    /**
     * The number of structural features of the '<em>SEFF2 Method Mapping</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SEFF2_METHOD_MAPPING_FEATURE_COUNT = 2;

    /**
     * The number of operations of the '<em>SEFF2 Method Mapping</em>' class. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SEFF2_METHOD_MAPPING_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '{@link org.somox.seff2javaast.impl.SEFF2JavaASTImpl
     * <em>SEFF2 Java AST</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.somox.seff2javaast.impl.SEFF2JavaASTImpl
     * @see org.somox.seff2javaast.impl.Seff2javaastPackageImpl#getSEFF2JavaAST()
     * @generated
     */
    int SEFF2_JAVA_AST = 1;

    /**
     * The feature id for the '<em><b>Seff2 Method Mappings</b></em>' containment reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SEFF2_JAVA_AST__SEFF2_METHOD_MAPPINGS = 0;

    /**
     * The number of structural features of the '<em>SEFF2 Java AST</em>' class. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SEFF2_JAVA_AST_FEATURE_COUNT = 1;

    /**
     * The number of operations of the '<em>SEFF2 Java AST</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SEFF2_JAVA_AST_OPERATION_COUNT = 0;

    /**
     * Returns the meta object for class '{@link org.somox.seff2javaast.SEFF2MethodMapping
     * <em>SEFF2 Method Mapping</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>SEFF2 Method Mapping</em>'.
     * @see org.somox.seff2javaast.SEFF2MethodMapping
     * @generated
     */
    EClass getSEFF2MethodMapping();

    /**
     * Returns the meta object for the reference '
     * {@link org.somox.seff2javaast.SEFF2MethodMapping#getBlockstatement <em>Blockstatement</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference '<em>Blockstatement</em>'.
     * @see org.somox.seff2javaast.SEFF2MethodMapping#getBlockstatement()
     * @see #getSEFF2MethodMapping()
     * @generated
     */
    EReference getSEFF2MethodMapping_Blockstatement();

    /**
     * Returns the meta object for the reference '
     * {@link org.somox.seff2javaast.SEFF2MethodMapping#getSeff <em>Seff</em>}'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference '<em>Seff</em>'.
     * @see org.somox.seff2javaast.SEFF2MethodMapping#getSeff()
     * @see #getSEFF2MethodMapping()
     * @generated
     */
    EReference getSEFF2MethodMapping_Seff();

    /**
     * Returns the meta object for class '{@link org.somox.seff2javaast.SEFF2JavaAST
     * <em>SEFF2 Java AST</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>SEFF2 Java AST</em>'.
     * @see org.somox.seff2javaast.SEFF2JavaAST
     * @generated
     */
    EClass getSEFF2JavaAST();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.somox.seff2javaast.SEFF2JavaAST#getSeff2MethodMappings
     * <em>Seff2 Method Mappings</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '<em>Seff2 Method Mappings</em>'.
     * @see org.somox.seff2javaast.SEFF2JavaAST#getSeff2MethodMappings()
     * @see #getSEFF2JavaAST()
     * @generated
     */
    EReference getSEFF2JavaAST_Seff2MethodMappings();

    /**
     * Returns the factory that creates the instances of the model. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the factory that creates the instances of the model.
     * @generated
     */
    Seff2javaastFactory getSeff2javaastFactory();

    /**
     * <!-- begin-user-doc --> Defines literals for the meta objects that represent
     * <ul>
     * <li>each class,</li>
     * <li>each feature of each class,</li>
     * <li>each operation of each class,</li>
     * <li>each enum,</li>
     * <li>and each data type</li>
     * </ul>
     * <!-- end-user-doc -->
     *
     * @generated
     */
    interface Literals {
        /**
         * The meta object literal for the '
         * {@link org.somox.seff2javaast.impl.SEFF2MethodMappingImpl <em>SEFF2 Method Mapping</em>}'
         * class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.somox.seff2javaast.impl.SEFF2MethodMappingImpl
         * @see org.somox.seff2javaast.impl.Seff2javaastPackageImpl#getSEFF2MethodMapping()
         * @generated
         */
        EClass SEFF2_METHOD_MAPPING = eINSTANCE.getSEFF2MethodMapping();

        /**
         * The meta object literal for the '<em><b>Blockstatement</b></em>' reference feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference SEFF2_METHOD_MAPPING__BLOCKSTATEMENT = eINSTANCE.getSEFF2MethodMapping_Blockstatement();

        /**
         * The meta object literal for the '<em><b>Seff</b></em>' reference feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference SEFF2_METHOD_MAPPING__SEFF = eINSTANCE.getSEFF2MethodMapping_Seff();

        /**
         * The meta object literal for the '{@link org.somox.seff2javaast.impl.SEFF2JavaASTImpl
         * <em>SEFF2 Java AST</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.somox.seff2javaast.impl.SEFF2JavaASTImpl
         * @see org.somox.seff2javaast.impl.Seff2javaastPackageImpl#getSEFF2JavaAST()
         * @generated
         */
        EClass SEFF2_JAVA_AST = eINSTANCE.getSEFF2JavaAST();

        /**
         * The meta object literal for the '<em><b>Seff2 Method Mappings</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference SEFF2_JAVA_AST__SEFF2_METHOD_MAPPINGS = eINSTANCE.getSEFF2JavaAST_Seff2MethodMappings();

    }

} // Seff2javaastPackage
