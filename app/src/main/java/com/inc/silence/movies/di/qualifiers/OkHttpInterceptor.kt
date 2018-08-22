package com.inc.silence.movies.di.qualifiers

import com.inc.silence.movies.di.scopes.ApplicationScope
import javax.inject.Qualifier

@Qualifier
@ApplicationScope
annotation class OkHttpInterceptor