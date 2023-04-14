package uk.nightlines.core.redux

interface  Middleware<State : Any> {

    suspend fun bind(
        action: Action,
        state: State,
        dispatch: (Action) -> Unit
    )
}
