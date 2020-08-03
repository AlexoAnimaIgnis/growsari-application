export class RoutingConstants {
    static readonly ROUTE_LOGIN = 'login';   
    static readonly ROUTE_DASHBOARD = 'view/board';

    static getRoute(routeName) : string {
        return '/' + routeName;
    }
}