import { combineReducers } from 'redux'
import { routeReducer } from 'redux-simple-router'
import { member} from './member'

const rootReducer = combineReducers(Object.assign({}, {
	member,
}, {
	routing: routeReducer
}))

export default rootReducer
