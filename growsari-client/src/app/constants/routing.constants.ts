export class RoutingConstants {
    static readonly ROUTE_LOGIN = 'login';   static getRoute(routeName) : string {
        return '/' + routeName;
    }
}