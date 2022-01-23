package codeninjas.musicakinator.other.base

abstract class BaseUseCase<REPOSITORY, ARG_TYPE, RETURN_TYPE>
constructor(protected val repository: REPOSITORY) {

    abstract fun createObservable(arg: ARG_TYPE? = null): RETURN_TYPE
}