package studio.forface.ermes.exceptions

import studio.forface.ermes.servicebuilder.Url

/* Author: Davide Giuseppe Farella */

/** An [Exception] about a wrong annotation */
class IllegalAnnotationException( message: String ): Exception( message )

/** An [Exception] about a missing annotation */
class MissingAnnotationException( message: String ): Exception( message )

/** An [Exception] about an invalid [Url] */
class InvalidUrlException( message: String ): Exception( message )