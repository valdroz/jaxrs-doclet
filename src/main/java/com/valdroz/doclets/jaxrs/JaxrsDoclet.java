package com.valdroz.doclets.jaxrs;

import com.sun.javadoc.AnnotationDesc;
import com.sun.javadoc.ClassDoc;
import com.sun.javadoc.MethodDoc;
import com.sun.javadoc.RootDoc;

/**
 * Created by Valerijus Drozdovas on 3/23/15.
 */
public class JaxrsDoclet {

    public static boolean start(RootDoc root) {
        ClassDoc[] classes = root.classes();

        String[][] options = root.options();

        System.out.println("========= Javadoc ============");

        for( String[] opvalues : options ) {
            for(String v : opvalues) {
                System.out.print("value = \"" + v + "\"; ");
            }
            System.out.println();
        }

        System.out.println("========= jaxrs resources ============");

        for (int i = 0; i < classes.length; ++i) {
            //System.out.println(classes[i]);
            ClassDoc cd = classes[i];
            AnnotationDesc[] annotationDescs = cd.annotations();
            for (AnnotationDesc ad : annotationDescs) {
                String aName = ad.annotationType().asClassDoc().name();
                String aQType = ad.annotationType().asClassDoc().qualifiedTypeName();
                if ( "javax.ws.rs.Path".equals(aQType) ) {
                    System.out.println("REST Resource API: " + cd.qualifiedTypeName());

                    MethodDoc[] methods = cd.methods();
                    for (MethodDoc methodDoc : methods ) {
                        System.out.println("Method:" + methodDoc.name() );
                        AnnotationDesc[] methodAnnotations = methodDoc.annotations();
                        for ( AnnotationDesc methodAnnotation : methodAnnotations ) {
                            System.out.println("Ann: " + methodAnnotation.annotationType().asClassDoc().qualifiedTypeName());
                            for (AnnotationDesc.ElementValuePair valuePair : methodAnnotation.elementValues()) {
                                System.out.println("\t " + valuePair.element().name() + " -> " + valuePair.value().value().getClass().getName());
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

    public static int optionLength(String option) {
        if(option.equals("-template")) {
            return 2;
        }
        return 0;
    }

}
